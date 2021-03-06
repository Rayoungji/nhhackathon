package org.nhhackaton.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.easypament.TransferApiService;
import org.nhhackaton.api.easypament.dto.DrawingTransferRequest;
import org.nhhackaton.api.easypament.dto.DrawingTransferResponse;
import org.nhhackaton.api.finaccount.FinAccountApiService;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountResponse;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountResponse;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.InvestPaymentRequest;
import org.nhhackaton.api.p2p.dto.InvestPaymentResponse;
import org.nhhackaton.api.p2p.dto.VirtualAccountRequest;
import org.nhhackaton.api.p2p.dto.VirtualAccountResponse;
import org.hibernate.type.IntegerType;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.*;
import org.nhhackaton.errors.exception.MemberNotFoundException;
import org.nhhackaton.errors.exception.UserDefineException;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.repository.InterestRepository;
import org.nhhackaton.invest.entity.Invest;
import org.nhhackaton.invest.repository.InvestRepository;
import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.loan.repository.LoanRepository;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final static float COFIX = 0.027f;

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final P2PApiService p2PApiService;
    private final InterestRepository interestRepository;
    private final FinAccountApiService finAccountApiService;
    private final TransferApiService transferApiService;
    private final InvestRepository investRepository;

    @Transactional
    public void executeLoan(Loan loan){

        Member receiver = memberRepository.findByIdentity(loan.getReceiverIdentity()).orElseThrow(MemberNotFoundException::new);
//
//        if(!receiver.isVerified())
//            throw new UserDefineException("검증 진행 후에 이용해주세요");

        Optional<Loan> foundLoan = loanRepository.findFirstByOrderByIdDesc();

        long loanNo;
        if(foundLoan.isPresent())
            loanNo = foundLoan.get().getLoanNo() + 1L;
        else
            loanNo = 1L;

        //투자자 정해서 업데이트
        List<Invest> 투자리스트 = getInvestorList(loan.getLoanPrice());

        List<Loan> loanList = 투자리스트.stream()
                .map(invest -> Loan.of(
                        loan,
                        invest.getInvestMember(),
                        invest.getInvestPrice(),
                        String.valueOf(
                                ((Float.parseFloat(invest.getInvestPrice()) * COFIX) / ChronoUnit.MONTHS.between(loan.getLoanDate(), loan.getEndDate()))
                        ),
                        loanNo)
                )
                .collect(Collectors.toList());

        loanRepository.saveAll(loanList);

        ResponseEntity<InvestPaymentResponse> investPaymentResponseResponseEntity = p2PApiService.executeInvest(
                InvestPaymentRequest.of(
                        투자리스트.stream()
                                .map(Invest::getInvestPrice)
                                .mapToInt(Integer::parseInt)
                                .sum(),
                        String.valueOf(ChronoUnit.MONTHS.between(loan.getLoanDate(), loan.getEndDate())),
                        String.valueOf(loan.getLoanNo()),
                        receiver.getBcCd(),
                        receiver.getAccountNum(),
                        receiver.getName(),
                        투자리스트.stream()
                            .map(invest -> InvestPaymentREC.of(invest.getInvestMember().getInvestVirtualAccount(), invest.getInvestPrice()))
                            .collect(Collectors.toList())
                )
        );

        System.out.println(investPaymentResponseResponseEntity.getBody().getHeader().getRsms());


        List<Invest> invests = 투자리스트.stream()
                .map(invest -> invest.update())
                .collect(Collectors.toList());

        investRepository.saveAll(invests);

        if (receiver.getRepaymentVirtualAccount() == null) {
            //차입자용 가상계좌 발급
            VirtualAccountRequest virtualAccountRequest = VirtualAccountRequest.builder()
                    .P2PVractUsg("2")
                    .P2PCmtmNo("0000000000")
                    .ChidSqno("0000000000")
                    .InvstBrwNm(receiver.getName()).build();

            makeLoanVirtualAccount(receiver, virtualAccountRequest);
        }
    }

    public void drawLoan(Member member, String loanPrice) {

        //투자 핀어카운트 -> 핀테크 약정계좌
        DrawingTransferRequest drawingTransferRequest = DrawingTransferRequest.builder()
                .MractOtlt("입금되었습니다")
                .DractOtlt("출금되었습니다")
                .Tram(loanPrice)
                .FinAcno(member.getFinAccount()).build();
        ResponseEntity<DrawingTransferResponse> drawingTransferResponse = transferApiService.draw(drawingTransferRequest);
        System.out.println(drawingTransferResponse.getBody().getRfsnYmd());  //투자 등록일자

        //TODO 핀테크 약정계좌 -> 투자용 가상계좌로 투자금 이체는 현재 테스트 불가

    }

    public void makeLoanVirtualAccount(Member member, VirtualAccountRequest virtualAccountRequest) {
        log.warn(" ========= MAKE LOAN VIRTUAL ACCOUNT START =============");
        ResponseEntity<VirtualAccountResponse> virtualAccountResponse = p2PApiService.create(virtualAccountRequest);
        System.out.println("VirtualAccount: " + virtualAccountResponse.getBody().getVran()); //가상계좌 발급
        member.setRepaymentVirtualAccount(virtualAccountResponse.getBody().getVran());
        log.warn(" ========= MAKE LOAN VIRTUAL ACCOUNT START =============");
    }

    public List<Interest> getInterestListByInvestor(String identity){
        Member member = memberRepository.findByIdentity(identity).orElseThrow(MemberNotFoundException::new);

        return interestRepository.findByInvestor(member.getIdentity());
    }

    public List<Interest> getInterestListByBorrower(String identity){
        return interestRepository.findByBorrower(identity);
    }

    public List<Loan> getLoan(String identity) {
        return loanRepository.findByReceiverIdentity(identity);
    }


    @Transactional
    public void executeRepayment(String loanNo){
        List<Loan> loanList = loanRepository.findByLoanNo(Long.parseLong(loanNo));

        Member receiver = memberRepository.findByIdentity(loanList.get(0).getReceiverIdentity()).orElseThrow(MemberNotFoundException::new);

        InterestRepaymentRequest interestRepaymentRequest =
                InterestRepaymentRequest.builder()
                    .P2pCmtmNo("0000000000")
                    .ChidSqno("0000000000")
                    .LoanNo(loanNo)
                    .RpaySumAmt(
                         String.valueOf(
                                 loanList.stream()
                                         .map(Loan::getLoanPrice)
                                         .mapToInt(Integer::parseInt)
                                         .sum()
                         )
                    ).Vran(receiver.getRepaymentVirtualAccount())
                    .RpayYmd(LocalDate.now().toString().replaceAll("-", ""))
                    .DractOtlt(receiver.getName() + "투자금 회수")
                    .MractOtlt("원금 상환")
                    .Rec(
                            loanList.stream()
                                    .map(loan -> InterestRepaymentREC.of(loan.getLoanMember().getInvestVirtualAccount(), loan.getLoanPrice()))
                                    .collect(Collectors.toList())
                    )
                    .build();


        ResponseEntity<InterestRepaymentResponse> interestRepaymentResponseResponseEntity = p2PApiService.executeInterest(interestRepaymentRequest);

        System.out.println(interestRepaymentResponseResponseEntity.getBody().getHeader().getRsms());


        loanList.stream()
                .forEach(loan -> returnDeposit(loan, loan.getLoanPrice(), false));

        loanList.stream()
                .map(loan -> loan.update())
                .forEach(loan -> loanRepository.save(loan));

    }


    private void returnDeposit(Loan loan, String price, boolean isInterest){

        DepositReturnRequest depositReturnRequest = DepositReturnRequest.builder()
                .P2pCmtmNo("0000000000")
                .ChidSqno("0000000000")
                .Vran(loan.getLoanMember().getInvestVirtualAccount())
                .RtnAmt(price)
                .Bncd(loan.getLoanMember().getBcCd())
                .Dpnm(loan.getLoanMember().getName())
                .IvstrAcct(loan.getLoanMember().getAccountNum())
                .MractOtlt(isInterest ? "이자입금" : "원금입금")
                .build();

        ResponseEntity<DepositReturnResponse> depositReturnResponseResponseEntity = p2PApiService.executeDepositReturn(depositReturnRequest);

    }

    private List<Invest> getInvestorList(String loanPrice){
        //TODO 매칭 알고리즘

        List<Invest> invests = investRepository.findAllByIsLoanFalseOrderByIdAsc();
        List<Invest> investCandidate = new ArrayList<>();

        long price = Long.parseLong(loanPrice);
        for(Invest invest : invests){

            if(Long.parseLong(invest.getInvestPrice()) < price){
                investCandidate.add(invest);
                price -= Long.parseLong(invest.getInvestPrice());
                continue;
            }else if(Long.parseLong(invest.getInvestPrice()) > price){
                continue;
            }else{
                investCandidate.add(invest);
                break;
            }
        }

        return investCandidate;
    }

}

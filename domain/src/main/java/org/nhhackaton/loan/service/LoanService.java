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
import org.nhhackaton.errors.exception.MemberNotFoundException;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.repository.InterestRepository;
import org.nhhackaton.invest.entity.Invest;
import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.loan.repository.LoanRepository;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final P2PApiService p2PApiService;
    private final InterestRepository interestRepository;
    private final FinAccountApiService finAccountApiService;
    private final TransferApiService transferApiService;

    public ResponseEntity<InvestPaymentResponse> executeLoan(Loan loan){

        if(memberRepository.findByIdentity(loan.getReceiverIdentity()).orElseThrow(MemberNotFoundException::new).isVerified())
            return ResponseEntity.noContent().build();

        Optional<Loan> foundLoan = loanRepository.findFirstByOrderByIdDesc();

        long loanNo;
        if(foundLoan.isPresent())
            loanNo = foundLoan.get().getLoanNo() + 1L;
        else
            loanNo = 1L;

        //투자자 정해서 업데이트
        List<Invest> 투자리스트 = getInvestorList(loan.getLoanPrice());

        List<Loan> loanList = 투자리스트.stream().
                map(invest -> Loan.of(loan, invest.getInvestMember(), invest.getInvestPrice(), "투자자가 받을 이자", loanNo))
                .collect(Collectors.toList());

        loanRepository.saveAll(loanList);

        return p2PApiService.executeInvest(
                        InvestPaymentRequest.of(
                                투자리스트.stream()
                                        .map(Invest::getInvestPrice)
                                        .mapToInt(Integer::parseInt)
                                        .sum(),
                                String.valueOf(loan.getLoanMonth()),
                                String.valueOf(loan.getLoanNo()),
                                loan.getReceiverBankCode(),
                                loan.getReceiverAccount(),
                                loan.getReceiver(),
                                Arrays.asList()
                        )
                );
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

        if (member.getRepaymentVirtualAccount() == null) {
            //차입자용 가상계좌 발급
            VirtualAccountRequest virtualAccountRequest = VirtualAccountRequest.builder()
                    .P2PVractUsg("2")
                    .P2PCmtmNo("0000000000")
                    .ChidSqno("0000000000")
                    .InvstBrwNm(member.getName()).build();

            makeLoanVirtualAccount(member, virtualAccountRequest);
        }

        //TODO 핀테크 약정계좌 -> 투자용 가상계좌로 투자금 이체는 현재 테스트 불가

    }

    public void makeLoanVirtualAccount(Member member, VirtualAccountRequest virtualAccountRequest) {
        log.warn(" ========= MAKE INVEST VIRTUAL ACCOUNT START =============");
        ResponseEntity<VirtualAccountResponse> virtualAccountResponse = p2PApiService.create(virtualAccountRequest);
        System.out.println("VirtualAccount: " + virtualAccountResponse.getBody().getVran()); //가상계좌 발급
        member.setInvestVirtualAccount(virtualAccountResponse.getBody().getVran());
        log.warn(" ========= MAKE INVEST VIRTUAL ACCOUNT START =============");
    }

    public List<Interest> getInterestListByInvestor(String identity){
        Member member = memberRepository.findByIdentity(identity).orElseThrow(MemberNotFoundException::new);

        return interestRepository.findByInvestor(member.getIdentity());
    }

    public List<Interest> getInterestListByBorrower(String identity){
        return interestRepository.findByBorrower(identity);
    }


    private List<Invest> getInvestorList(String loanPrice){
        //TODO 매칭 알고리즘
        return new ArrayList<>();
    }
}

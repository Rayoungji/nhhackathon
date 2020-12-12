package org.nhhackaton.loan.service;

import lombok.RequiredArgsConstructor;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final static float COFIX = 0.027f;

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final P2PApiService p2PApiService;
    private final InterestRepository interestRepository;
    private final InvestRepository investRepository;

    @Transactional
    public void executeLoan(Loan loan){

        if(!memberRepository.findByIdentity(loan.getReceiverIdentity()).orElseThrow(MemberNotFoundException::new).isVerified())
            throw new UserDefineException("검증 진행 후에 이용해주세요");

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
                                ((Float.parseFloat(invest.getInvestPrice()) * COFIX) / ChronoUnit.MONTHS.between(loan.getEndDate(), loan.getLoanDate()))
                        ),
                        loanNo)
                )
                .collect(Collectors.toList());

        loanRepository.saveAll(loanList);

        p2PApiService.executeInvest(
                InvestPaymentRequest.of(
                        투자리스트.stream()
                                .map(Invest::getInvestPrice)
                                .mapToInt(Integer::parseInt)
                                .sum(),
                        String.valueOf(ChronoUnit.MONTHS.between(loan.getEndDate(), loan.getLoanDate())),
                        String.valueOf(loan.getLoanNo()),
                        loan.getReceiverBankCode(),
                        loan.getReceiverAccount(),
                        loan.getReceiver(),
                        Arrays.asList()
                )
        );

        List<Invest> invests = 투자리스트.stream()
                .map(invest -> invest.update())
                .collect(Collectors.toList());

        investRepository.saveAll(invests);
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

    public void executeRepayment(String loanNo){
        List<Loan> loanList = loanRepository.findByLoanNo(loanNo);

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
                    )
                    .RpayYmd(LocalDate.now().toString().replaceAll("-", ""))
                    .DractOtlt(loanList.get(0).getReceiver() + "투자금 회수")
                    .MractOtlt("원금 상환")
                    .Rec(
                            loanList.stream()
                                    .map(loan -> InterestRepaymentREC.of(loan.getReceiverAccount(), loan.getLoanPrice()))
                                    .collect(Collectors.toList())
                    )
                    .build();


        ResponseEntity<InterestRepaymentResponse> interestRepaymentResponseResponseEntity = p2PApiService.executeInterest(interestRepaymentRequest);
        //TODO 투자 예치금 반환

    }

    public void executeInterestRepayment(String loanNo){
        List<Loan> loanList = loanRepository.findByLoanNo(loanNo);

        InterestRepaymentRequest interestRepaymentRequest =
                InterestRepaymentRequest.builder()
                        .P2pCmtmNo("0000000000")
                        .ChidSqno("0000000000")
                        .LoanNo(loanNo)
                        .RpaySumAmt(
                                String.valueOf(
                                        loanList.stream()
                                                .map(Loan::getInterest)
                                                .mapToDouble(Float::parseFloat)
                                                .sum()
                                )
                        )
                        .RpayYmd(LocalDate.now().toString().replaceAll("-", ""))
                        .DractOtlt(LocalDate.now().getMonth().name()+ "월분 이자")
                        .MractOtlt("이자 상환")
                        .Rec(
                                loanList.stream()
                                        .map(loan -> InterestRepaymentREC.of(loan.getReceiverAccount(), loan.getInterest()))
                                        .collect(Collectors.toList())
                        )
                        .build();


        ResponseEntity<InterestRepaymentResponse> interestRepaymentResponseResponseEntity = p2PApiService.executeInterest(interestRepaymentRequest);
        //TODO 투자 예치금 반환
    }

    private List<Invest> getInvestorList(String loanPrice){
        //TODO 매칭 알고리즘
        return new ArrayList<>();
    }

}

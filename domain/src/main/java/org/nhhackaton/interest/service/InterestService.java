package org.nhhackaton.interest.service;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.easypament.TransferApiService;
import org.nhhackaton.api.finaccount.FinAccountApiService;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.*;
import org.nhhackaton.errors.exception.MemberNotFoundException;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.repository.InterestRepository;
import org.nhhackaton.invest.repository.InvestRepository;
import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.loan.repository.LoanRepository;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final P2PApiService p2PApiService;
    private final InterestRepository interestRepository;

    @Transactional
    public boolean executeInterestRepayment(String loanNo){

        try {
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
                                                    .map(Loan::getInterest)
                                                    .mapToDouble(Float::parseFloat)
                                                    .sum()
                                    )
                            )
                            .Vran(receiver.getRepaymentVirtualAccount())
                            .RpayYmd(LocalDate.now().toString().replaceAll("-", ""))
                            .DractOtlt(LocalDate.now().getMonthValue() + "월분 이자")
                            .MractOtlt("이자 상환")
                            .Rec(
                                    loanList.stream()
                                            .map(loan -> InterestRepaymentREC.of(loan.getLoanMember().getInvestVirtualAccount(), loan.getInterest()))
                                            .collect(Collectors.toList())
                            )
                            .build();


            ResponseEntity<InterestRepaymentResponse> interestRepaymentResponseResponseEntity = p2PApiService.executeInterest(interestRepaymentRequest);
            System.out.println("원리금 상환 : " + interestRepaymentResponseResponseEntity.getBody().getHeader().getRsms());

            loanList.stream()
                    .forEach(loan -> {
                        loanRepository.save(loan.updateCount());
//                        interestRepository.save(
//                                Interest.builder()
//                                        .borrower(receiver.getIdentity())
//                                        .investor(loan.getLoanMember().getIdentity())
//                                        .repaymentDate(LocalDate.now())
//                                        .repaymentPrice(loan.getInterest())
//                                        .build());
                        returnDeposit(loan, loan.getInterest(), true);
                    });
        }catch (Exception e){
            return false;
        }
        return true;
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

        p2PApiService.executeDepositReturn(depositReturnRequest);

    }

}

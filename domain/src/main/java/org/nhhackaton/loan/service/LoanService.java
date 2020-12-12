package org.nhhackaton.loan.service;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.InvestPaymentRequest;
import org.nhhackaton.api.p2p.dto.InvestPaymentResponse;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final P2PApiService p2PApiService;
    private final InterestRepository interestRepository;

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

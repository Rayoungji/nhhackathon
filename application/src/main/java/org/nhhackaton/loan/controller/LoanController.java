package org.nhhackaton.loan.controller;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.deposit.dto.BaseResponse;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.dto.DrawLoanRequest;
import org.nhhackaton.loan.dto.LoanExecuteRequest;
import org.nhhackaton.loan.service.LoanService;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {


    private final LoanService loanService;
    private final MemberService memberService;

    @PostMapping
    public void executeLoan(@RequestBody LoanExecuteRequest loanExecuteRequest){
        loanService.executeLoan(loanExecuteRequest.of());
    }

    @GetMapping("/investor/{identity}")
    public List<Interest> getInterestListByInvestor(@PathVariable String identity){
        return loanService.getInterestListByInvestor(identity);
    }

    @GetMapping("/borrower/{identity}")
    public List<Interest> getInterestListByBorrower(@PathVariable String identity){
        return loanService.getInterestListByBorrower(identity);
    }

    @PostMapping("/draw-loan/{identity}")
    public BaseResponse drawLoan(@PathVariable String identity, @RequestBody DrawLoanRequest drawLoanRequest) {
        Member loginMember = memberService.getMemberByIdentity(identity);
        loanService.drawLoan(loginMember,drawLoanRequest.getLoanPrice());
        return new BaseResponse("200");
    }

}

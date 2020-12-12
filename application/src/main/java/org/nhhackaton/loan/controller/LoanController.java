package org.nhhackaton.loan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.dto.LoanExecuteRequest;
import org.nhhackaton.loan.dto.LoanResponse;
import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.loan.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {


    private final LoanService loanService;

    @ApiOperation("투자금 지급 지시")
    @PostMapping
    public void executeLoan(@RequestBody LoanExecuteRequest loanExecuteRequest){
        loanService.executeLoan(loanExecuteRequest.of());
    }

    @ApiOperation("투자자 아이디를 통해서 투자자가 받은 이자 목록 보기")
    @GetMapping("/investor/{identity}")
    public List<Interest> getInterestListByInvestor(@PathVariable String identity){
        return loanService.getInterestListByInvestor(identity);
    }

    @ApiOperation("차입자 아이디를 통해서 차입자가 상환한 이자 목록 보기")
    @GetMapping("/borrower/{identity}")
    public List<Interest> getInterestListByBorrower(@PathVariable String identity){
        return loanService.getInterestListByBorrower(identity);
    }

    @ApiOperation("대출금 조회")
    @GetMapping("/{identity}")
    public List<LoanResponse> getLoan(@PathVariable String identity){
        List<Loan> loans = loanService.getLoan(identity);
        List<Interest> interests = loanService.getInterestListByBorrower(identity);

        return LoanResponse.of(loans, interests);
    }

    @PostMapping("/repayment/{loanNo}")
    public void executeRepayment(@PathVariable String loanNo){
        //TODO 원리금 상환 추가해야함
        loanService.executeRepayment(loanNo);
    }

    @PostMapping("/interest/repayment")
    public void executeInterestRepayment(@PathVariable String loanNo){
        //TODO 원리금 상환 추가해야함
        loanService.executeInterestRepayment(loanNo);
    }

}

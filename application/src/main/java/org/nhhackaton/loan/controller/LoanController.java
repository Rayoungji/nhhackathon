package org.nhhackaton.loan.controller;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.dto.LoanExecuteRequest;
import org.nhhackaton.loan.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {


    private final LoanService loanService;

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

}

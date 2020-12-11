package org.nhhackaton.deposit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.finaccount.FinAccountApiService;
import org.nhhackaton.api.finaccount.dto.ApplyInvestRequest;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/deposit")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final FinAccountApiService FinAccountApiService;

    @PostMapping("/invest")
    public ResponseEntity putDepositMoneyToVirtualAccount(@RequestBody ApplyInvestRequest applyInvestRequest) {

        OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                .DrtrRgyn(applyInvestRequest.getDrtrRgyn())
                .BrdtBrno(applyInvestRequest.getBrdtBrno())
                .Bncd(applyInvestRequest.getBncd())
                .Acno(applyInvestRequest.getAcno()).build();

        ResponseEntity<OpenFinAccountResponse> call = FinAccountApiService.open(openFinAccountRequest);
        System.out.println(call.getBody().getHeader().getRsms());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity getBalanceMoneyFromVirtualAccount(){
        return null;
    }

}

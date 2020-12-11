package org.nhhackaton.deposit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.pinaccount.dto.ApplyInvestRequest;
import org.nhhackaton.api.pinaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.api.pinaccount.PinAccountCreateService;
import org.nhhackaton.api.pinaccount.dto.OpenFinAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/deposit")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final ApiCallService apiCallService;
    private final PinAccountCreateService pinAccountCreateService;

    @PostMapping("/invest")
    public ResponseEntity putDepositMoneyToVirtualAccount(@RequestBody ApplyInvestRequest applyInvestRequest) {

        OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                .DrtrRgyn(applyInvestRequest.getDrtrRgyn())
                .BrdtBrno(applyInvestRequest.getBrdtBrno())
                .Bncd(applyInvestRequest.getBncd())
                .Acno(applyInvestRequest.getAcno()).build();

        ResponseEntity<OpenFinAccountResponse> call = pinAccountCreateService.call(openFinAccountRequest);
        System.out.println(call.getBody().getHeader().getRsms());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity getBalanceMoneyFromVirtualAccount(){
        return null;
    }

}

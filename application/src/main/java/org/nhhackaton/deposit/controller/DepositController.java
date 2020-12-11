package org.nhhackaton.deposit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.deposit.request.CheckAccountRequest;
import org.nhhackaton.deposit.request.PutDepositRequest;
import org.nhhackaton.deposit.response.CheckAccountResponse;
import org.nhhackaton.deposit.response.DataResponse;
import org.nhhackaton.deposit.response.OpenAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/deposit")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final ApiCallService apiCallService;

    @PostMapping("/invest")
    public ResponseEntity putDepositMoneyToVirtualAccount(@RequestBody PutDepositRequest putDepositRequest) {
        ResponseEntity<OpenAccountResponse> openAccountResponse = apiCallService.post(ApiName.OPEN_ACCOUNT,putDepositRequest.of(), DataResponse.class);
        CheckAccountRequest checkAccountRequest = new CheckAccountRequest(openAccountResponse.getBody().getRgno(), putDepositRequest.getBrdtBrno());
        //ResponseEntity<CheckAccountResponse> checkAccountResponse = apiCallService.post(ApiName.CHECK_ACCOUNT,checkAccountRequest, CheckAccountResponse.class);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity getBalanceMoneyFromVirtualAccount(){
        return null;
    }

}

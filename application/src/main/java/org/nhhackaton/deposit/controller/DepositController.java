package org.nhhackaton.deposit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.deposit.request.CheckAccountRequest;
import org.nhhackaton.deposit.request.HeaderRequest;
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
        HeaderRequest headerRequest = HeaderRequest.builder()
                .Tsymd("OpenFinAccountDirect")
                .Trtm("20201211")
                .IsTuno("01111111")
                .Iscd("000671")
                .FintechApsno("001")
                .ApiSvcCd("DrawingTransferA")
                .ApiNm("OpenFinAccountDirect")
                .AccessToken("e053c94bd5b1b0cf188b61dfc9b0378a857c56c15245a15da1cd8a72322e5342").build();
        putDepositRequest.setHeader(headerRequest);
         apiCallService.post(ApiName.OPEN_ACCOUNT,putDepositRequest);
        //CheckAccountRequest checkAccountRequest = new CheckAccountRequest(openAccountResponse.getBody().getRgno(), putDepositRequest.getBrdtBrno());
        //ResponseEntity<CheckAccountResponse> checkAccountResponse = apiCallService.post(ApiName.CHECK_ACCOUNT,checkAccountRequest, CheckAccountResponse.class);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity getBalanceMoneyFromVirtualAccount(){
        return null;
    }

}

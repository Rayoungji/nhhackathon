package org.nhhackaton.api.finaccount;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountResponse;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinAccountApiService {
    private final ApiCallService apiCallService;

    public ResponseEntity<CheckFinAccountResponse> check(CheckFinAccountRequest checkFinAccountRequest){
        return apiCallService.post(ApiName.CHECK_ACCOUNT, checkFinAccountRequest, CheckFinAccountResponse.class);
    }

    public ResponseEntity<OpenFinAccountResponse> open(OpenFinAccountRequest openFinAccountRequest){
        return apiCallService.post(ApiName.OPEN_ACCOUNT, openFinAccountRequest, OpenFinAccountResponse.class);
    }
}

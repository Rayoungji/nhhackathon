package org.nhhackaton.api.pinaccount;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.api.pinaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.api.pinaccount.dto.OpenFinAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PinAccountCreateService {
    private final ApiCallService apiCallService;

    public ResponseEntity<OpenFinAccountResponse> call(OpenFinAccountRequest openFinAccountRequest){
        return apiCallService.post(ApiName.OPEN_ACCOUNT, openFinAccountRequest, OpenFinAccountResponse.class);
    }
}

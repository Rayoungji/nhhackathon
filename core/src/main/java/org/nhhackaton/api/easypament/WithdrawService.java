package org.nhhackaton.api.easypament;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.api.HeaderRequestParent;
import org.nhhackaton.api.easypament.dto.DrawingTransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final ApiCallService apiCallService;

    public ResponseEntity<DrawingTransferResponse> call(HeaderRequestParent body){
        return apiCallService.post(ApiName.Drawing_Transfer, body, DrawingTransferResponse.class);
    }
}

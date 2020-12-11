package org.nhhackaton.api.easypament;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.api.easypament.dto.DrawingTransferRequest;
import org.nhhackaton.api.easypament.dto.DrawingTransferResponse;
import org.nhhackaton.api.easypament.dto.ReceivedTransferRequest;
import org.nhhackaton.api.easypament.dto.ReceivedTransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferApiService {

    private final ApiCallService apiCallService;

    public ResponseEntity<DrawingTransferResponse> draw(DrawingTransferRequest drawingTransferRequest){
        return apiCallService.post(ApiName.Drawing_Transfer, drawingTransferRequest, DrawingTransferResponse.class);
    }

    public ResponseEntity<ReceivedTransferResponse> receive(ReceivedTransferRequest receivedTransferRequest){
        return apiCallService.post(ApiName.Received_Transfer, receivedTransferRequest, ReceivedTransferResponse.class);
    }
}

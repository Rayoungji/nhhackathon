package org.nhhackaton.api.p2p;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.ApiName;
import org.nhhackaton.api.p2p.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class P2PApiService {
    private final ApiCallService apiCallService;

    public ResponseEntity<VirtualAccountResponse> create(VirtualAccountRequest virtualAccountRequest){
        return apiCallService.post(ApiName.VIRTUAL_ACCOUNT, virtualAccountRequest, VirtualAccountResponse.class);
    }

    public ResponseEntity<ReceiveInformationResponse> getInfoList(ReceiveInformationRequest receiveInformationRequest){
        return apiCallService.post(ApiName.RECEIVER_INFORMATION, receiveInformationRequest, ReceiveInformationResponse.class);
    }

    public ResponseEntity<InvestPaymentResponse> executeInvest(InvestPaymentRequest investPaymentRequest){
        return apiCallService.post(ApiName.INVEST_PAYMENTS, investPaymentRequest, InvestPaymentResponse.class);
    }

    public ResponseEntity<InvestResultResponse> getInvestResult(InvestResultRequest investResultRequest){
        return apiCallService.post(ApiName.INVEST_RESULT, investResultRequest, InvestResultResponse.class);
    }

    public ResponseEntity<InterestRepaymentResponse> executeInterest(InterestRepaymentRequest interestRepaymentRequest){
        return apiCallService.post(ApiName.INTEREST_REPAYMENT, interestRepaymentRequest, InterestRepaymentResponse.class);
    }

    public ResponseEntity<InterestResultResponse> getInterestResult(InterestResultRequest interestResultRequest){
        return apiCallService.post(ApiName.INTEREST_RESULT, interestResultRequest, InterestResultResponse.class);
    }

    public ResponseEntity<DepositReturnResponse> executeDepositReturn(DepositReturnRequest depositReturnRequest){
        return apiCallService.post(ApiName.DEPOSIT_RETURN, depositReturnRequest, DepositReturnResponse.class);
    }

    public ResponseEntity<DepositResultResponse> getDepositReturnResult(DepositResultRequest depositResultRequest){
        return apiCallService.post(ApiName.DEPOSIT_RESULT, depositResultRequest, DepositResultResponse.class);
    }


}

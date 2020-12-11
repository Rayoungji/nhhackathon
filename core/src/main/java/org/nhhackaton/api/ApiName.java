package org.nhhackaton.api;

import lombok.Getter;

@Getter
public enum ApiName {
    VIRTUAL_ACCOUNT("P2PNVirtualAccountNumberRequest.nh"),
    RECEIVER_INFORMATION("P2PNAccountReceiveInformationList.nh"),
    INVEST_PAYMENTS("P2PNInvestmentPaymentOrder.nh"),
    INVEST_RESULT("P2PNInvestmentLoanExecutionResultInquiry.nh"),
    INTEREST_REPAYMENT("P2PNInterestRepayment.nh"),
    INTEREST_RESULT("P2PNInterestPaymentAccountReceiveResultConfirmation.nh"),
    DEPOSIT_RETURN("P2PNInvestmentDepositAmountReturn.nh"),
    DEPOSIT_RESULT("P2PNInvestmentDepositAmountReturnResultList.nh"),
    OPEN_ACCOUNT("OpenFinAccountDirect.nh"),
    CHECK_ACCOUNT("CheckFinAccountDirect.nh"),
    Drawing_Transfer("DrawingTransfer.nh"),
    Received_Transfer("ReceivedTransferAccountNumber.nh");

    private String name;

    ApiName(String name) {
        this.name = name;
    }
}

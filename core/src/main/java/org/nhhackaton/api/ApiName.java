package org.nhhackaton.api;

import lombok.Getter;

@Getter
public enum ApiName {
    VIRTUAL_ACCOUNT("P2PNVirtualAccountNumberRequest"),
    RECEIVER_INFORMATION("P2PNAccountReceiveInformationList"),
    INVEST_PAYMENTS("P2PNInvestmentPaymentOrder"),
    INVEST_RESULT("P2PNInvestmentLoanExecutionResultInquiry"),
    INTEREST_REPAYMENT("P2PNInterestRepayment"),
    INTEREST_RESULT("P2PNInterestPaymentAccountReceiveResultConfirmation"),
    DEPOSIT_RETURN("P2PNInvestmentDepositAmountReturn"),
    DEPOSIT_RESULT("P2PNInvestmentDepositAmountReturnResultList");

    private String name;

    ApiName(String name) {
        this.name = name;
    }
}

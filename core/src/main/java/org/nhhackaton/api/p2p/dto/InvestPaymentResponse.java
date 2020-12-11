package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class InvestPaymentResponse {
    @JsonProperty("Header")
    HeaderResponse Header;

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("LoanNo")
    private String LoanNo;
    @JsonProperty("InvAccAmt")
    private String InvAccAmt;
    @JsonProperty("InvSumAmt")
    private String InvSumAmt;
}

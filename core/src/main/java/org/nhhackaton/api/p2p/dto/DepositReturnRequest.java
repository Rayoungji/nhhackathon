package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
public class DepositReturnRequest extends HeaderRequestParent {
    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("Vran")
    private String Vran;
    @JsonProperty("RtnAmt")
    private String RtnAmt;
    @JsonProperty("Bncd")
    private String Bncd;
    @JsonProperty("Dpnm")
    private String Dpnm;
    @JsonProperty("IvstrAcct")
    private String IvstrAcct;
    @JsonProperty("MractOtlt")
    private String MractOtlt;
}

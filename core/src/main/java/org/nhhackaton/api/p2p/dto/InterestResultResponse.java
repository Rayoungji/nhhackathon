package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class InterestResultResponse {
    @JsonProperty("Header")
    private HeaderResponse Header;
    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("LoanNo")
    private String LoanNo;
    @JsonProperty("Pcrs")
    private String Pcrs;
    @JsonProperty("Prtm")
    private String Prtm;
}

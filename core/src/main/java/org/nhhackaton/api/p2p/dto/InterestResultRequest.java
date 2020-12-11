package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
public class InterestResultRequest extends HeaderRequestParent {
    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("LoanNo")
    private String LoanNo;
    @JsonProperty("OrtrYmd")
    private String OrtrYmd;
    @JsonProperty("OrtrIsTuno")
    private String OrtrIsTuno;
}

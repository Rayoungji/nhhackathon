package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
public class VirtualAccountRequest extends HeaderRequestParent {

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;

    @JsonProperty("P2PVractUsg")
    private String P2PVractUsg;

    @JsonProperty("ChidSqno")
    private String ChidSqno;

    @JsonProperty("InvstBrwNm")
    private String InvstBrwNm;
}

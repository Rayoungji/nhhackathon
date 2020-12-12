package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualAccountRequest extends HeaderRequestParent {

    @JsonProperty("P2PCmtmNo")
    private String P2PCmtmNo;

    @JsonProperty("P2PVractUsg")
    private String P2PVractUsg;

    @JsonProperty("ChidSqno")
    private String ChidSqno;

    @JsonProperty("InvstBrwNm")
    private String InvstBrwNm;
}

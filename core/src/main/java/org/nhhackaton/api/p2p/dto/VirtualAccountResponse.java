package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class VirtualAccountResponse {

    @JsonProperty("Header")
    private HeaderResponse Header;

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;

    @JsonProperty("ChidSqno")
    private String ChidSqno;

    @JsonProperty("Vran")
    private String Vran;

    @JsonProperty("P2PVractUsg")
    private String P2PVractUsg;

    @JsonProperty("InvstBrwNm")
    private String InvstBrwNm;
}

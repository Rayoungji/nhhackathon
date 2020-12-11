package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReceiveInformationResponse {

    @JsonProperty("Header")
    private HeaderResponse Header;

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;

    @JsonProperty("ChidSqno")
    private String ChidSqno;

    @JsonProperty("UseAblAmt")
    private String UseAblAmt;

    @JsonProperty("ThdDepsAmt")
    private String ThdDepsAmt;

    @JsonProperty("TotCnt")
    private String TotCnt;

    @JsonProperty("Iqtcnt")
    private String Iqtcnt;

    @JsonProperty("PageNo")
    private String PageNo;

    @JsonProperty("REC")
    private List<ReceiveInformationREC> REC;

}

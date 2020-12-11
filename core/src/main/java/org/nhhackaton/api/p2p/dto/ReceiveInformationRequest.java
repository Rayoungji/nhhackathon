package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
public class ReceiveInformationRequest extends HeaderRequestParent {

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;

    @JsonProperty("ChidSqno")
    private String ChidSqno;

    @JsonProperty("Vran")
    private String Vran;

    @JsonProperty("Iqds")
    private String Iqds;

    @JsonProperty("Insymd")
    private String Insymd;

    @JsonProperty("Ineymd")
    private String Ineymd;

    @JsonProperty("PageNo")
    private String PageNo;

}

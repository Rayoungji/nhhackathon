package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiveInformationREC {

    @JsonProperty("Sqno")
    private String Sqno;

    @JsonProperty("Vran")
    private String Vran;

    @JsonProperty("RcvYmd")
    private String RcvYmd;

    @JsonProperty("RcvAmt")
    private String RcvAmt;
}

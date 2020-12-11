package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvestPaymentREC {

    @JsonProperty("Vran")
    private String Vran;

    @JsonProperty("InvAmt")
    private String InvAmt;
}

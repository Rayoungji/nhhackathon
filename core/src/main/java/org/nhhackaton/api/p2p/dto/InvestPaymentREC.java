package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class InvestPaymentREC {

    @JsonProperty("Vran")
    private String Vran;

    @JsonProperty("InvAmt")
    private String InvAmt;
    
    public static InvestPaymentREC of(String account, String amount){
        return InvestPaymentREC.builder()
                .Vran(account)
                .InvAmt(amount)
                .build();
    }
}

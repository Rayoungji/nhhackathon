package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class InterestRepaymentREC {

    @JsonProperty("Vran")
    private String Vran;
    @JsonProperty("RpayAmt")
    private String RpayAmt;

    public static InterestRepaymentREC of(String account, String price){
        return InterestRepaymentREC.builder()
                .Vran(account)
                .RpayAmt(price)
                .build();
    }
}

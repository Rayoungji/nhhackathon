package org.nhhackaton.loan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrawLoanRequest {

    @JsonProperty("loanPrice")
    private String loanPrice;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;
}

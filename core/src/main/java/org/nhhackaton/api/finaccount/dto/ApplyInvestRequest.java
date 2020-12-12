package org.nhhackaton.api.finaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhhackaton.api.HeaderRequestParent;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplyInvestRequest {

    @JsonProperty("investPrice")
    private String investPrice;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;
}

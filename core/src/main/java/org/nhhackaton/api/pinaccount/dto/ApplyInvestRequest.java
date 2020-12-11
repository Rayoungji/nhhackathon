package org.nhhackaton.api.pinaccount.dto;

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
public class ApplyInvestRequest extends HeaderRequestParent {
    //핀어카운트 발금에 필요한 계좌 정보 + 투자할 금액

    @JsonProperty("investPrice")
    private String investPrice;

    @JsonProperty("DrtrRgyn")
    private String DrtrRgyn;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;
}

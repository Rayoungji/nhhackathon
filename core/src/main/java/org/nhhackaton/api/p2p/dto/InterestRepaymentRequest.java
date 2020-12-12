package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class InterestRepaymentRequest extends HeaderRequestParent {

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("LoanNo")
    private String LoanNo;
    @JsonProperty("RpaySumAmt")
    private String RpaySumAmt;
    @JsonProperty("RpayYmd")
    private String RpayYmd;
    @JsonProperty("Vran")
    private String Vran;
    @JsonProperty("DractOtlt")
    private String DractOtlt;
    @JsonProperty("MractOtlt")
    private String MractOtlt;
    @JsonProperty("Rec")
    private List<InterestRepaymentREC> Rec;

}

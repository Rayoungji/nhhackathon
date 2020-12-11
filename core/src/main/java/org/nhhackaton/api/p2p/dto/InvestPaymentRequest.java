package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

import java.util.List;

@Getter
@NoArgsConstructor
public class InvestPaymentRequest extends HeaderRequestParent {

    @JsonProperty("P2pCmtmNo")
    private String P2pCmtmNo;
    @JsonProperty("ChidSqno")
    private String ChidSqno;
    @JsonProperty("SlctAmt")
    private String SlctAmt;
    @JsonProperty("LonTrm")
    private String LonTrm;
    @JsonProperty("InvSumAmt")
    private String InvSumAmt;
    @JsonProperty("NewTrnsYn")
    private String NewTrnsYn;
    @JsonProperty("LoanNo")
    private String LoanNo;
    @JsonProperty("Bncd")
    private String Bncd;
    @JsonProperty("BrwAcno")
    private String BrwAcno;
    @JsonProperty("Dpnm")
    private String Dpnm;
    @JsonProperty("LonApcYmd")
    private String LonApcYmd;
    @JsonProperty("DractOtlt")
    private String DractOtlt;
    @JsonProperty("MractOtlt")
    private String MractOtlt;
    @JsonProperty("Rec")
    private List<InvestPaymentREC> Rec;
}

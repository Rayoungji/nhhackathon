package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public static InvestPaymentRequest of(int loanAmount,
                                          String term,
                                          String loanNo,
                                          String studentBankCode,
                                          String studentAccount,
                                          String studentName,
                                          List<InvestPaymentREC> rec){
        return InvestPaymentRequest.builder()
                .P2pCmtmNo("0000000000")
                .ChidSqno("0000000000")
                .SlctAmt(String.valueOf(loanAmount))
                .LonTrm(term)
                .InvSumAmt(String.valueOf(loanAmount))
                .NewTrnsYn("Y")
                .LoanNo(loanNo)
                .Bncd(studentBankCode)
                .BrwAcno(studentAccount)
                .Dpnm(studentName)
                .LonApcYmd(LocalDate.now().toString().replaceAll("-", ""))
                .DractOtlt(studentName + "투자금")
                .MractOtlt("투자금 지급")
                .Rec(rec)
                .build();

    }
}

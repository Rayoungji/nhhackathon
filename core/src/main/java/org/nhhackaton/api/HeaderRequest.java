package org.nhhackaton.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeaderRequest {

    @JsonProperty("ApiNm")
    private String ApiNm;

    @JsonProperty("Tsymd")
    private String Tsymd;

    @JsonProperty("Trtm")
    private String Trtm;

    @JsonProperty("Iscd")
    private String Iscd;

    @JsonProperty("FintechApsno")
    private String FintechApsno;

    @JsonProperty("ApiSvcCd")
    private String ApiSvcCd;

    @JsonProperty("IsTuno")
    private String IsTuno;

    @JsonProperty("AccessToken")
    private String AccessToken;

    public static HeaderRequest of(String api, String[] date) {
        return HeaderRequest.builder()
                .ApiNm(api)
                .Tsymd(date[0].replaceAll("-", ""))
                .Trtm(date[1].replaceAll(":", ""))
                .IsTuno(String.format("%09d", Math.random()*10000000))
                .Iscd("000671")
                .FintechApsno("001")
                .ApiSvcCd("DrawingTransferA")
                .AccessToken("e053c94bd5b1b0cf188b61dfc9b0378a857c56c15245a15da1cd8a72322e5342")
                .build();
    }
}

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
                .IsTuno(String.format("%09f", Math.random()*10000000))
                .Iscd("000737")
                .FintechApsno("001")
                .ApiSvcCd("DrawingTransferA")
                .AccessToken("78c9e2d30d0168ca73ef880090061f96671b05a50661369a2abdf66dd625f8be")
                .build();
    }
}

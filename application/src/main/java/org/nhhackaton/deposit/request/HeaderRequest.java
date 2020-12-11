package org.nhhackaton.deposit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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


}

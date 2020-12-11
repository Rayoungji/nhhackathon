package org.nhhackaton.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeaderResponse {
    @JsonProperty("Trtm")
    private String Trtm;
    @JsonProperty("Rsms")
    private String Rsms;
    @JsonProperty("ApiNm")
    private String ApiNm;
    @JsonProperty("IsTuno")
    private String IsTuno;
    @JsonProperty("Tsymd")
    private String Tsymd;
    @JsonProperty("FintechApsno")
    private String FintechApsno;
    @JsonProperty("Iscd")
    private String Iscd;
    @JsonProperty("Rpcd")
    private String Rpcd;
    @JsonProperty("ApiSvcCd")
    private String ApiSvcCd;
}

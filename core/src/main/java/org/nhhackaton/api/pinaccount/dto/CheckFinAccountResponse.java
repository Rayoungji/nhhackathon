package org.nhhackaton.api.pinaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.nhhackaton.api.HeaderResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckFinAccountResponse {

    @JsonProperty("Header")
    private HeaderResponse Header;

    @JsonProperty("FinAcno")
    private String FinAcno;

    @JsonProperty("RgsnYmd")
    private String RgsnYmd;
}

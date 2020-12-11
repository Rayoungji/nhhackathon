package org.nhhackaton.api.easypament.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class DrawingTransferResponse {

    @JsonProperty("Header")
    private HeaderResponse Header;

    @JsonProperty("FinAcno")
    private String FinAcno;

    @JsonProperty("RgsnYmd")
    private String RfsnYmd;
}

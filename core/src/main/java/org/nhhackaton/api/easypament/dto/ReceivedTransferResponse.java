package org.nhhackaton.api.easypament.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class ReceivedTransferResponse {
    @JsonProperty("Header")
    HeaderResponse Header;
}

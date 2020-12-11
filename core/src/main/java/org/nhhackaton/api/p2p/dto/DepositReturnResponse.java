package org.nhhackaton.api.p2p.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderResponse;

@Getter
@NoArgsConstructor
public class DepositReturnResponse {
    @JsonProperty("Header")
    private HeaderResponse Header;
}

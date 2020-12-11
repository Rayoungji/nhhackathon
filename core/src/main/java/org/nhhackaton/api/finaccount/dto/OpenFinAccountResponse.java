package org.nhhackaton.api.finaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhhackaton.api.HeaderResponse;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OpenFinAccountResponse {

    @JsonProperty("Rgno")
    private String Rgno;

    @JsonProperty("Header")
    private HeaderResponse Header;
}

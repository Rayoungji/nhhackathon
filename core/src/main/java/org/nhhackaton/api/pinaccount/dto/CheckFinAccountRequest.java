package org.nhhackaton.api.pinaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckFinAccountRequest {
    @JsonProperty("Rgno")
    private String Rgno;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;
}

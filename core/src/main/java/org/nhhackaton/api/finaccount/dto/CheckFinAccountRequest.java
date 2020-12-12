package org.nhhackaton.api.finaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.nhhackaton.api.HeaderRequestParent;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckFinAccountRequest extends HeaderRequestParent {
    @JsonProperty("Rgno")
    private String Rgno;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;
}

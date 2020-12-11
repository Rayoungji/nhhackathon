package org.nhhackaton.api.finaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhhackaton.api.HeaderRequestParent;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckFinAccountRequest extends HeaderRequestParent {
    @JsonProperty("Rgno")
    private String Rgno;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;
}

package org.nhhackaton.api.pinaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenFinAccountRequest extends HeaderRequestParent {

    @JsonProperty("DrtrRgyn")
    private String DrtrRgyn;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;

}

package org.nhhackaton.deposit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataResponse {

    @JsonProperty("header")
    private HeaderResponse header;

    private String Rgno;
}

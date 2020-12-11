package org.nhhackaton.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeaderRequestParent {

    @JsonProperty("Header")
    private HeaderRequest headerRequest;

    public void setHeader(HeaderRequest headerRequest){
        this.headerRequest = headerRequest;
    }
}

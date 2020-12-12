package org.nhhackaton.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SetAccountRequest {

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;
    
}

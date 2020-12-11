package org.nhhackaton.deposit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutDepositRequest {

    @JsonProperty("DrtrRgyn")
    private String DrtrRgyn;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;

    public MultiValueMap<String, String> of(){
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("DrtrRgyn", this.DrtrRgyn);
        parameters.add("BrdtBrno", this.BrdtBrno);
        parameters.add("Bncd", this.Bncd);
        parameters.add("Acno", this.Acno);

        return parameters;
    }

}

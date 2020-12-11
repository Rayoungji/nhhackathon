package org.nhhackaton.api.easypament.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.api.HeaderRequestParent;

@Getter
@NoArgsConstructor
public class ReceivedTransferRequest extends HeaderRequestParent {

    @JsonProperty("Bncd")
    private String Bncd;
    @JsonProperty("Acno")
    private String Acno;
    @JsonProperty("Tram")
    private String Tram;
    private String DractOtlt;
    private String MractOtlt;

    public void setContent(String withdraw, String deposit){
        this.DractOtlt = withdraw;
        this.MractOtlt = deposit;
    }
}

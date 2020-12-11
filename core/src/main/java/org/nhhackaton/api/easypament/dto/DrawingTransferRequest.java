package org.nhhackaton.api.easypament.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.nhhackaton.api.HeaderRequestParent;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DrawingTransferRequest extends HeaderRequestParent {

    @JsonProperty("FinAcno")
    private String FinAcno;

    @JsonProperty("Tram")
    private String Tram;

    @JsonProperty("DractOtlt")
    private String DractOtlt;

    @JsonProperty("MractOtlt")
    private String MractOtlt;

    public void setContent(String withdraw, String deposit){
        this.DractOtlt = withdraw;
        this.MractOtlt = deposit;
    }

}

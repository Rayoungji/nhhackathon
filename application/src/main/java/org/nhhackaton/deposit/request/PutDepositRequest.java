package org.nhhackaton.deposit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.nhhackaton.deposit.response.HeaderResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutDepositRequest {

    @JsonProperty("Header")
    private HeaderRequest Header;

    @JsonProperty("DrtrRgyn")
    private String DrtrRgyn;

    @JsonProperty("BrdtBrno")
    private String BrdtBrno;

    @JsonProperty("Bncd")
    private String Bncd;

    @JsonProperty("Acno")
    private String Acno;

//    public MultiValueMap<String, Object> of(){
//        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
//
//        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
//        headers.add("ApiNm", "OpenFinAccountDirect");
//        headers.add("Tsymd", "20201211");
//        headers.add("Trtm", "112428");
//        headers.add("Iscd","000671");
//        headers.add("FintechApsno", "001");
//        headers.add("APISvcCd", "DrawingTransferA");
//        headers.add("Istuno", String.format("%06d", "1234"));
//        headers.add("AccessToken", "e053c94bd5b1b0cf188b61dfc9b0378a857c56c15245a15da1cd8a72322e5342");
//
//        parameters.add("Header", hea);
//        parameters.add("DrtrRgyn", this.DrtrRgyn);
//        parameters.add("BrdtBrno", this.BrdtBrno);
//        parameters.add("Bncd", this.Bncd);
//        parameters.add("Acno", this.Acno);
//
//        return parameters;
//    }

}

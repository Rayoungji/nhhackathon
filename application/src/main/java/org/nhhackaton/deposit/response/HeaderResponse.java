package org.nhhackaton.deposit.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeaderResponse {
//"Trtm": "112428",
//    "Rsms": "정상처리 되었습니다.",
//    "ApiNm": "OpenFinAccountDirect",
//    "IsTuno": "01111111",
//    "Tsymd": "20201211",
//    "FintechApsno": "001",
//    "Iscd": "000671",
//    "Rpcd": "00000",
//    "ApiSvcCd": "DrawingTransferA"
    private String Trtm;
    private String Rsms;
    private String ApiNm;
    private String IsTuno;
    private String Tsymd;
    private String FintechApsno;
    private String Iscd;
    private String Rpcd;
    private String ApiSvcCd;

}

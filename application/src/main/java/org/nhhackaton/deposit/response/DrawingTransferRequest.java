package org.nhhackaton.deposit.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawingTransferRequest {
    private String FinAcno;
    private String Tram;
}

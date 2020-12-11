package org.nhhackaton.deposit.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckAccountResponse {

    private String FinAcno;
    private String RgsnYmd;
}

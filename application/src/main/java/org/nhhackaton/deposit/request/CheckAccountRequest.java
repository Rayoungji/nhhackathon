package org.nhhackaton.deposit.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckAccountRequest {
    private String Rgno;
    private String BrdtBrno;
}

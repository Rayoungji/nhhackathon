package org.nhhackaton.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AlreadyInvestResponse {
    private String loanPrice;
    private String loanDate;
}

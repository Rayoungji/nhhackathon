package org.nhhackaton.chun.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class InterestRepayDAO {
    private int id;
    private int loanNo;
    private int interest;
    private String accessToken;
}

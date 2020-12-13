package org.nhhackaton.chun.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
@AllArgsConstructor
public class InterestReaderDAO {
    private String borrower;
    private String investor;
    private String loanNo;
    private String interest;
    private String accessToken;
}

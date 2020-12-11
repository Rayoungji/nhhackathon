package org.nhhackaton.Interest;

import org.nhhackaton.member.Member;

import java.time.LocalDate;


//이자
public class Interest {
    private Member loanMember;
    private Member investMember;
    private int interestPrice;
    private LocalDate interestDate;
    private InterestType type;
}

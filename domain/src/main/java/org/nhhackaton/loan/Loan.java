package org.nhhackaton.loan;

import org.nhhackaton.member.Member;

import java.time.LocalDate;

//대출
public class Loan {
    private Member loanMember;
    private int loanNumber;
    private int loanPrice;
    private Member investMember;
    private int investPrice;
    private LocalDate deadLine;
    private  double InterestRate;

}

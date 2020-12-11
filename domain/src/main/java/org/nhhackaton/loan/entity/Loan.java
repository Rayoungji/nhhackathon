package org.nhhackaton.loan.entity;

import org.nhhackaton.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LOANS")
public class Loan {

    @Id @Column(name = "LOAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member loanMember;  //투자자

    @Embedded
    private Investor investor; //투자자리스트
    private String loanPrice;  //대출금액
    private LocalDate loanDate;  //대출일
    private String interest;  //이자
    private int loanMonth;  //대출개월수
    private int repayCount;  //이자상환횟수
    private Boolean isRepay;  //원금상환여부

}

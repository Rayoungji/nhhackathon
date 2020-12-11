package org.nhhackaton.member.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERS")
public class Member {

    @Id @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identity;
    private String password;
    private String investVirtualAccount;
    private String repaymentVirtualAccount;
    private String investFinAccount;
    private String repaymentFinAccount;


    @Builder
    public Member(String identity, String password, String investVirtualAccount, String repaymentVirtualAccount, String investFinAccount, String repaymentFinAccount) {
        this.identity = identity;
        this.password = password;
        this.investVirtualAccount = investVirtualAccount;
        this.repaymentVirtualAccount = repaymentVirtualAccount;
        this.investFinAccount = investFinAccount;
        this.repaymentFinAccount = repaymentFinAccount;
    }
}

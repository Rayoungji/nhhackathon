package org.nhhackaton.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MEMBERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private boolean isVerified;

    @Builder
    public Member(String identity,
                  String password,
                  String investVirtualAccount,
                  String repaymentVirtualAccount,
                  String investFinAccount,
                  String repaymentFinAccount,
                  boolean isVerified) {
        this.identity = identity;
        this.password = password;
        this.investVirtualAccount = investVirtualAccount;
        this.repaymentVirtualAccount = repaymentVirtualAccount;
        this.investFinAccount = investFinAccount;
        this.repaymentFinAccount = repaymentFinAccount;
        this.isVerified = isVerified;
    }
}

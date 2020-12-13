package org.nhhackaton.member.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MEMBERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identity;
    private String password;
    private String birthday;
    private String name;
    private String investVirtualAccount;
    private String repaymentVirtualAccount;
    private String finAccount;
    private String bcCd;
    private String accountNum;
    private String fcmToken;
    private boolean isVerified;

    public void validate() {
        this.isVerified = !this.isVerified;
    }

    public void setFinAccount(String finAccount) {
        this.finAccount = finAccount;
    }

    public void setAccountInfo(String bcCd, String accountNum) {
        this.bcCd = bcCd;
        this.accountNum = accountNum;
    }

    public void setInvestVirtualAccount(String investVirtualAccount) {
        this.investVirtualAccount = investVirtualAccount;
    }

    public void setRepaymentVirtualAccount(String repaymentVirtualAccount) {
        this.repaymentVirtualAccount = repaymentVirtualAccount;
    }

    public void update(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}

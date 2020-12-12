package org.nhhackaton.member.entity;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERS")
@Getter
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
    private String investFinAccount;
    private String repaymentFinAccount;


    public void setInvestFinAccount(String investFinAccount) {
        this.investFinAccount = investFinAccount;
    }

    public void setRepaymentFinAccount(String repaymentFinAccount) {
        this.repaymentFinAccount = repaymentFinAccount;
    }

    public void setInvestVirtualAccount(String investVirtualAccount) {
        this.investVirtualAccount = investVirtualAccount;
    }
}

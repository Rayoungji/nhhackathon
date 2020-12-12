package org.nhhackaton.invest.entity;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.nhhackaton.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "INVESTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Invest {

    @Id @Column(name = "INVEST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member investMember;  //투자자

    private String investPrice; //투자금액
    private String investDate; //투자날짜
    private Boolean isLoan; //대출여부


}

package org.nhhackaton.interest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "INTEREST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String borrower;
    private String investor;
    private LocalDate repaymentDate;
    private String repaymentPrice;

}

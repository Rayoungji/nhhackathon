package org.nhhackaton.interest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "INTEREST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Interest {

    @Id
    @Column(name = "INTEREST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String borrower;
    private String investor;
    private LocalDate repaymentDate;
    private String repaymentPrice;

}

package org.nhhackaton.loan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "LOANS")
@NoArgsConstructor
@AllArgsConstructor @Builder
public class Loan {

    @Id @Column(name = "LOAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    private Member loanMember;  //투자자

    private String loanPrice;  //투자자가 얼마를 대출해줬는지
    private LocalDate loanDate;  //대출일
    private String interest;  //이자
    private LocalDate endDate;  //대출개월수
    private int repayCount;  //이자상환횟수
    private Boolean isRepay;  //원금상환여부
    private long loanNo;
    private String receiverIdentity;

    public static Loan of(Loan loan,
                          Member investor,
                          String loanPrice,
                          String interest,
                          long loanNo){
        return Loan.builder()
                .loanDate(loan.getLoanDate())
                .endDate(loan.getEndDate())
                .isRepay(loan.getIsRepay())
                .repayCount(loan.getRepayCount())
                .receiverIdentity(loan.getReceiverIdentity())
                .loanMember(investor)
                .loanPrice(loanPrice)
                .interest(interest)
                .loanNo(loanNo)
                .build();
    }

    public Loan update(){
        this.isRepay = !this.isRepay;
        return this;
    }

}

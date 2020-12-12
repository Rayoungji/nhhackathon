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
    private int loanMonth;  //대출개월수
    private int repayCount;  //이자상환횟수
    private Boolean isRepay;  //원금상환여부
    private long loanNo;
    private String receiverIdentity;
    private String receiver;
    private String receiverBankCode;
    private String receiverAccount;

    public static Loan of(Loan loan, Member member, String loanPrice, String interest, long loanNo){
        return Loan.builder()
                .loanDate(loan.getLoanDate())
                .loanMonth(loan.getLoanMonth())
                .isRepay(loan.getIsRepay())
                .repayCount(loan.getRepayCount())
                .receiver(loan.getReceiver())
                .receiverAccount(loan.getReceiverAccount())
                .receiverBankCode(loan.getReceiverBankCode())
                .receiverIdentity(loan.getReceiverIdentity())
                .loanMember(member)
                .loanPrice(loanPrice)
                .interest(interest)
                .loanNo(loanNo)
                .build();
    }

}

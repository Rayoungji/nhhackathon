package org.nhhackaton.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.entity.Loan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class LoanResponse {

    private String type;
    private long loanNo;
    private String amount;
    private String date;
    private String term;
    private int repayCount;

    public static List<LoanResponse> of(List<Loan> loans, List<Interest> interests){
        List<LoanResponse> list = new ArrayList<>();
        list.add(of(loans));
        interests.stream()
                .map(LoanResponse::of)
                .forEach(list::add);
        return list;
    }

    private static LoanResponse of(List<Loan> loans){
        return LoanResponse.builder()
                .type("대출")
                .amount(
                        String.valueOf(
                            loans.stream()
                            .map(Loan::getLoanPrice)
                            .mapToInt(Integer::parseInt)
                            .sum()
                        )
                )
                .date(loans.get(0).getLoanDate().toString())
                .loanNo(loans.get(0).getLoanNo())
                .term(String.valueOf(ChronoUnit.MONTHS.between(loans.get(0).getEndDate(), loans.get(0).getLoanDate())))
                .repayCount(loans.get(0).getRepayCount())
                .build();
    }

    private static LoanResponse of(Interest interest){
        return LoanResponse.builder()
                .type("상환")
                .amount(interest.getRepaymentPrice())
                .date(interest.getRepaymentDate().toString())
                .build();
    }


}

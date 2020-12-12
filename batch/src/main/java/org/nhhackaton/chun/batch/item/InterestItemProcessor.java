package org.nhhackaton.chun.batch.item;

import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.item.ItemProcessor;

public class InterestItemProcessor implements ItemProcessor<Loan, Loan> {
    @Override
    public Loan process(Loan item) throws Exception {
        return null;
    }
}

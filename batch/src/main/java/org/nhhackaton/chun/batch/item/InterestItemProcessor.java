package org.nhhackaton.chun.batch.item;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.dao.InterestRepayDAO;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.service.InterestService;
import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class InterestItemProcessor implements ItemProcessor<InterestRepayDAO, Interest> {
    private final InterestService interestService;
    @Override
    public Interest process(InterestRepayDAO item) throws Exception {
        
        return null;
    }
}

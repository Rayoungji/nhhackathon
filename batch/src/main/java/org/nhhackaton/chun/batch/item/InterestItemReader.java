package org.nhhackaton.chun.batch.item;

import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.item.database.JpaPagingItemReader;

public class InterestItemReader extends JpaPagingItemReader<Loan> {
}

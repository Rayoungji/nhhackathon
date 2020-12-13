package org.nhhackaton.chun.batch.item;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.dao.InterestRepayDAO;
import org.nhhackaton.chun.dao.InterestRepayMapper;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InterestItemReader extends JdbcCursorItemReader<InterestRepayDAO> {
    private final String REPAY_LIST_QUERY = "SELECT members.identity, loans.interest\n" +
            "FROM test.loans\n" +
            "join members\n" +
            "on loans.member_id = members.member_id\n" +
            "WHERE end_date > now();";
    private final JdbcTemplate jdbcTemplate;
    private final InterestRepayMapper mapper;

    @Override
    public InterestRepayDAO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<InterestRepayDAO> data = jdbcTemplate.query(REPAY_LIST_QUERY, mapper);
        return null;
    }
}

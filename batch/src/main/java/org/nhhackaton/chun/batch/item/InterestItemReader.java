package org.nhhackaton.chun.batch.item;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.dao.InterestReaderDAO;
import org.nhhackaton.chun.dao.InterestReaderDAOMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@StepScope
@Configuration
@RequiredArgsConstructor
public class InterestItemReader implements ItemReader<List<InterestReaderDAO>> {
    private final static String SQL = "SELECT t1.borrower, t1.interest, t1.loan_no, t1.investor, members.fcm_token\n" +
            "FROM (\n" +
            "\tSELECT members.identity as borrower, loans.interest, loans.loan_no, loans.receiver_identity as investor\n" +
            "\tFROM loans\n" +
            "\tjoin members on loans.member_id = members.member_id\n" +
            "\tWHERE end_date > now()\n" +
            "\tgroup by loans.loan_no\n" +
            ") t1\n" +
            "join members on t1.investor = members.identity;";

    private final DataSource dataSource;
    private final InterestReaderDAOMapper mapper;

    @Override
    public List<InterestReaderDAO> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(SQL, mapper);
    }
}

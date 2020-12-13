package org.nhhackaton.chun.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestReaderDAOMapper implements RowMapper<InterestReaderDAO> {
    @Override
    public InterestReaderDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.getRow() == 0)
            return null;
        return InterestReaderDAO.builder()
                .borrower(rs.getString("borrower"))
                .investor(rs.getString("investor"))
                .loanNo(rs.getString("loan_no"))
                .accessToken(rs.getString("fcm_token"))
                .interest(rs.getString("interest"))
                .build();
    }
}

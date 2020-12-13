package org.nhhackaton.chun.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestRepayMapper implements RowMapper<InterestRepayDAO> {
    @Override
    public InterestRepayDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return InterestRepayDAO.builder()
                .id(rs.getInt("identity"))
                .loanNo(rs.getInt("loanNo"))
                .accessToken(rs.getString("fcmtoken"))
                .interest(Integer.parseInt(rs.getString("interest")))
                .build();
    }
}

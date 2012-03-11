package org.cookmap.robot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class DefaultCookService {
	
	private String INSERT_INGRIDIENT_SQL = "insert into ingridients (name) values (?)";
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}
	
	public void addIngridients(final List<String> ingridients) {
	
        jdbcTemplate.batchUpdate(
                INSERT_INGRIDIENT_SQL,

                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, ingridients.get(i));
                    }

                    @Override
                    public int getBatchSize() {
                        return ingridients.size();
                    }
                }
        );		
		
	}

}

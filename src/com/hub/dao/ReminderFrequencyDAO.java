package com.hub.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hub.models.ReminderFrequency;

@Repository
public class ReminderFrequencyDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public ReminderFrequency findReminderFrequencyById(long id){
		List<ReminderFrequency> results = new ArrayList<ReminderFrequency>();
		
		String SQL = "SELECT * "
				+ "FROM reminder_frequency "
				+ "WHERE id=?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL,
				ReminderFrequency.getRowMapper(),
				id);
		
		return results.get(0);
	}
}

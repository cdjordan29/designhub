package com.hub.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hub.models.UserGroup;

@Repository 
public class UserGroupDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public UserGroup findUserGroupById(long id) {
		List<UserGroup> results = new ArrayList<UserGroup>();
		
		String SQL = "SELECT * "
				+ "FROM user_group "
				+ "WHERE id=?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL,
				UserGroup.getRowMapper(),
				id);
		
		return results.get(0);
	}
}

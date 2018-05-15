package com.hub.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.User;

@Repository
public class UserDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public User findUserById(long id){
		List<User> results = new ArrayList<User>();
		
		String SQL = "SELECT * "
				+ "FROM users "
				+ "WHERE id=?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL,
				User.getRowMapper(),
				id);
		
		return results.get(0);
	}
	
	public User findByUsername(String username){
		List<User> results = new ArrayList<User>();
		
		String SQL = "SELECT * "
				+ "FROM users "
				+ "WHERE email=?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL,
				User.getRowMapper(),
				username);
		
		return results.get(0);
		
	}
	
	public boolean updateUser(User u) {
		String SQL = "UPDATE users SET first_name=?, last_name=?, email=?, password=?, create_date = ?, is_active=?, permission=? where ID=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getCreateDate(), u.isActive(), u.getPermission(), u.getId());
		return rowsAffected == 1;
	}
	
	public boolean createUser(String firstName, String lastName, String email, String password, String permission){
		int result = 0;
		
		String SQL = "INSERT INTO users (first_name, last_name, email, password, create_date, is_active, permission) VALUES (?, ?, ?, ?, ?, true, ?)";
		Date date = new Date();
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		result = jdbcTemplate.update(SQL, firstName, lastName, email, password, date, permission);
		
		return result == 1;
	}
}
	

package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.Group;

@Repository
public class GroupDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//Generates ID, don't use it.
	public boolean addGroup(Group g){
		String SQL = "INSERT INTO group (name, is_active) VALUES (?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, g.getName(), g.isActive());
		
		return rowsAffected == 1;
	}
	
	public Group getGroupById(long id){
		List<Group> results = new ArrayList<Group>();
		String SQL = "SELECT * FROM group WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Group.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<Group> getAllGroups(){
		List<Group> results = new ArrayList<Group>();
		String SQL = "SELECT * FROM group";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Group.getRowMapper());
		return results;
	}
	
	public boolean updateGroup(Group g){
		String SQL = "UPDATE group SET name=?, is_active=? where ID=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, g.getName(), g.isActive(), g.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteGroup(Group g){
		String SQL = "DELETE FROM group WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, g.getId());
		return rowsAffected == 1;
	}
	
}

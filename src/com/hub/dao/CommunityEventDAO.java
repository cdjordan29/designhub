package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.CommunityEvent;

@Repository
public class CommunityEventDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addCommunityEvent(CommunityEvent ce){
		String SQL = "INSERT INTO community_event (date, description) VALUES (?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, ce.getDate(), ce.getDescription());
		
		return rowsAffected == 1;
	}
	
	public CommunityEvent getCommunityEventById(long id){
		List<CommunityEvent> results = new ArrayList<CommunityEvent>();
		String SQL = "SELECT * FROM community_event WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, CommunityEvent.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<CommunityEvent> getAllCommunityEvents(){
		List<CommunityEvent> results = new ArrayList<CommunityEvent>();
		String SQL = "SELECT * FROM community_event";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, CommunityEvent.getRowMapper());
		return results;
	}
	
	public boolean updateCommunityEvent(CommunityEvent ce){
		String SQL = "UPDATE community_event SET description=?, date=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, ce.getDescription(), ce.getDate(), ce.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteCommunityEvent(CommunityEvent ce){
		String SQL = "DELETE FROM community_event WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, ce.getId());
		return rowsAffected == 1;
	}
}

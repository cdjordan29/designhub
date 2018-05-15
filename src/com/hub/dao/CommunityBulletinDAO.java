  package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.CommunityBulletin;

@Repository
public class CommunityBulletinDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//Generates ID, don't use it.
	public boolean addCommunityBulletin(CommunityBulletin cb){
		String SQL = "INSERT INTO community_bulletin (description, thumbs_up_count, thumbs_down_count) VALUES (?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, cb.getDescription(), cb.getThumbsUp(), cb.getThumbsDown());
		
		return rowsAffected == 1;
	}
	
	public CommunityBulletin getCommunityBulletinById(long id){
		List<CommunityBulletin> results = new ArrayList<CommunityBulletin>();
		String SQL = "SELECT * FROM community_bulletin WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, CommunityBulletin.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<CommunityBulletin> getAllCommunityBulletins(){
		List<CommunityBulletin> results = new ArrayList<CommunityBulletin>();
		String SQL = "SELECT * FROM community_bulletin";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, CommunityBulletin.getRowMapper());
		return results;
	}
	
	public boolean updateCommunityBulletin(CommunityBulletin cb){
		String SQL = "UPDATE community_bulletin SET description=?, thumbs_up_count=?, thumbs_down_count=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, cb.getDescription(), cb.getThumbsUp(), cb.getThumbsDown(), cb.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteCommunityBulletin(CommunityBulletin cb){
		String SQL = "DELETE FROM community_bulletin WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, cb.getId());
		return rowsAffected == 1;
	}
	
}

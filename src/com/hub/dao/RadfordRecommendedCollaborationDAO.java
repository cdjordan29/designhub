package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.RadfordRecommendedCollaboration;

@Repository
public class RadfordRecommendedCollaborationDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		String SQL = "INSERT INTO radford_recommended_collaboration (title, description, download, image) VALUES (?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrc.getTitle(), rrc.getDescription(), rrc.getDownload(), rrc.getImage());
		
		return rowsAffected == 1;
	}
	
	public RadfordRecommendedCollaboration getRadfordRecommendedCollaborationById(long id){
		List<RadfordRecommendedCollaboration> results = new ArrayList<RadfordRecommendedCollaboration>();
		String SQL = "SELECT * FROM radford_recommended_collaboration WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, RadfordRecommendedCollaboration.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<RadfordRecommendedCollaboration> getAllRadfordRecommendedCollaborations(){
		List<RadfordRecommendedCollaboration> results = new ArrayList<RadfordRecommendedCollaboration>();
		String SQL = "SELECT * FROM radford_recommended_collaboration";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, RadfordRecommendedCollaboration.getRowMapper());	
		return results;
	}
	
	public boolean updateRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		String SQL = "UPDATE radford_recommended_collaboration SET title=?, description=?, download=?, image=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrc.getTitle(), rrc.getDescription(), rrc.getDownload(), rrc.getImage(), rrc.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		String SQL = "DELETE FROM radford_recommended_collaboration WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrc.getId());
		return rowsAffected == 1;
	}
}

package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.RadfordRecommendedReading;

@Repository
public class RadfordRecommendedReadingDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addRadfordRecommendedReading(RadfordRecommendedReading rrr){
		String SQL = "INSERT INTO radford_recommended_reading (title, download, buy_link, image, description, thumbs_up_count, thumbs_down_count) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrr.getTitle(), rrr.getDownload(), rrr.getBuyLink(), rrr.getImage(), rrr.getDescription(), rrr.getThumbsUp(), rrr.getThumbsDown());
		
		return rowsAffected == 1;
	}
	
	public RadfordRecommendedReading getRadfordRecommendedReadingById(long id){
		List<RadfordRecommendedReading> results = new ArrayList<RadfordRecommendedReading>();
		String SQL = "SELECT * FROM radford_recommended_reading WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, RadfordRecommendedReading.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<RadfordRecommendedReading> getAllRadfordRecommendedReadings(){
		List<RadfordRecommendedReading> results = new ArrayList<RadfordRecommendedReading>();
		String SQL = "SELECT * FROM radford_recommended_reading";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, RadfordRecommendedReading.getRowMapper());	
		return results;
	}
	
	public boolean updateRadfordRecommendedReading(RadfordRecommendedReading rrr){
		String SQL = "UPDATE radford_recommended_reading SET title=?, download=?, buy_link=?, image=?, description=?, thumbs_up_count=?, thumbs_down_count=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrr.getTitle(), rrr.getDownload(), rrr.getBuyLink(), rrr.getImage(), rrr.getDescription(), rrr.getThumbsUp(), rrr.getThumbsDown(), rrr.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteRadfordRecommendedReading(RadfordRecommendedReading rrr){
		String SQL = "DELETE FROM radford_recommended_reading WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, rrr.getId());
		return rowsAffected == 1;
	}
}

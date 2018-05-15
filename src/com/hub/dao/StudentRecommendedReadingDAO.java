package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.StudentRecommendedReading;

@Repository
public class StudentRecommendedReadingDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addStudentRecommendedReading(StudentRecommendedReading srr){
		String SQL = "INSERT INTO student_recommended_reading (title, buy_link, description, image, thumbs_up_count, thumbs_down_count) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, srr.getTitle(), srr.getBuyLink(), srr.getDescription(), srr.getImage(), srr.getThumbsUp(), srr.getThumbsDown());
		
		return rowsAffected == 1;
	}
	
	public StudentRecommendedReading getStudentRecommendedReadingById(long id){
		List<StudentRecommendedReading> results = new ArrayList<StudentRecommendedReading>();
		String SQL = "SELECT * FROM student_recommended_reading WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, StudentRecommendedReading.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<StudentRecommendedReading> getAllStudentRecommendedReadings(){
		List<StudentRecommendedReading> results = new ArrayList<StudentRecommendedReading>();
		String SQL = "SELECT * FROM student_recommended_reading";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, StudentRecommendedReading.getRowMapper());	
		return results;
	}
	
	public boolean updateStudentRecommendedReading(StudentRecommendedReading srr){
		String SQL = "UPDATE student_recommended_reading SET title=?, buy_link=?, description=?, image=?, thumbs_up_count=?, thumbs_down_count=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, srr.getTitle(), srr.getBuyLink(), srr.getDescription(), srr.getImage(), srr.getThumbsUp(), srr.getThumbsDown(), srr.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteStudentRecommendedReading(StudentRecommendedReading srr){
		String SQL = "DELETE FROM student_recommended_reading WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, srr.getId());
		return rowsAffected == 1;
	}
}

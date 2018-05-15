package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.StudentRecommendedCollaboration;

@Repository
public class StudentRecommendedCollaborationDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		String SQL = "INSERT INTO student_recommended_collaboration (title, description, download, image, thumbs_up_count, thumbs_down_count) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, src.getTitle(), src.getDescription(), src.getDownload(), src.getImage(), src.getThumbsUp(), src.getThumbsDown());
		
		return rowsAffected == 1;
	}
	
	public StudentRecommendedCollaboration getStudentRecommendedCollaborationById(long id){
		List<StudentRecommendedCollaboration> results = new ArrayList<StudentRecommendedCollaboration>();
		String SQL = "SELECT * FROM student_recommended_collaboration WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, StudentRecommendedCollaboration.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<StudentRecommendedCollaboration> getAllStudentRecommendedCollaborations(){
		List<StudentRecommendedCollaboration> results = new ArrayList<StudentRecommendedCollaboration>();
		String SQL = "SELECT * FROM student_recommended_collaboration";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, StudentRecommendedCollaboration.getRowMapper());	
		return results;
	}
	
	public boolean updateStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		String SQL = "UPDATE student_recommended_collaboration SET title=?, description=?, download=?, image=?, thumbs_up_count=?, thumbs_down_count=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, src.getTitle(), src.getDescription(), src.getDownload(), src.getImage(), src.getThumbsUp(), src.getThumbsDown(), src.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		String SQL = "DELETE FROM student_recommended_collaboration WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, src.getId());
		return rowsAffected == 1;
	}
}

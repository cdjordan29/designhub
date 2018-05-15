package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.Technique;
import com.hub.models.TechniqueSubCategory;

@Repository
public class TechniqueDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addTechnique(Technique t){
		String SQL = "INSERT INTO technique (title, description) VALUES (?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, t.getTitle(), t.getDescription());
		
		return rowsAffected == 1;
	}
	
	//Does not get SubCategory
	public Technique getTechniqueById(long id){
		List<Technique> results = new ArrayList<Technique>();
		String SQL = "SELECT * FROM technique WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Technique.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<Technique> getAllTechniques(){
		List<Technique> results = new ArrayList<Technique>();
		String SQL = "SELECT * FROM technique";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Technique.getRowMapper());	
		return results;
	}
	
	public List<Technique> getAllTechniquesInSubCategory(TechniqueSubCategory tsc){
		List<Technique> results = new ArrayList<Technique>();
		String SQL = "SELECT * FROM technique WHERE sub_category_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Technique.getRowMapper(), tsc.getId());	
		return results;
	}
	
	public boolean updateTechnique(Technique t){
		String SQL = "UPDATE technique SET title=?, description=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, t.getTitle(), t.getDescription(), t.getId());
		return rowsAffected == 1;
	}
	
	public boolean deleteTechnique(Technique t){
		String SQL = "DELETE FROM technique WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, t.getId());
		return rowsAffected == 1;
	}
}

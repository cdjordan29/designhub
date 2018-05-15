package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.TechniqueCategory;
import com.hub.services.TechniqueSubCategoryService;

@Repository
public class TechniqueCategoryDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	TechniqueSubCategoryService techniqueSubCategoryService;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	//Generates ID, don't use it.
	public boolean addTechniqueCategory(TechniqueCategory tc){
		String SQL = "INSERT INTO tech_category (name) VALUES (?)";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tc.getName());

		return rowsAffected == 1;
	}

	public TechniqueCategory getTechniqueCategoryById(long id){
		List<TechniqueCategory> results = new ArrayList<TechniqueCategory>();
		String SQL = "SELECT * FROM tech_category WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);

		results = jdbcTemplate.query(SQL, TechniqueCategory.getRowMapper(), id);
		drillDown(results);
		return results.get(0);
	}

	public List<TechniqueCategory> getAllTechniqueCategories(){
		List<TechniqueCategory> results = new ArrayList<TechniqueCategory>();
		String SQL = "SELECT * FROM tech_category";
		jdbcTemplate = new JdbcTemplate(dataSource);

		results = jdbcTemplate.query(SQL, TechniqueCategory.getRowMapper());
		drillDown(results);
		return results;
	}
	
	public void drillDown(List<TechniqueCategory> toDrill){
		for(TechniqueCategory tc: toDrill){
			tc.setSubCategories(techniqueSubCategoryService.getAllTechniqueSubCategorysInCategory(tc));
		}
	}

	public boolean updateTechniqueCategory(TechniqueCategory tc){
		String SQL = "UPDATE tech_category SET name=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tc.getName(), tc.getId());
		return rowsAffected == 1;
	}

	public boolean deleteTechniqueCategory(TechniqueCategory tc){
		String SQL = "DELETE FROM tech_category WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tc.getId());
		return rowsAffected == 1;
	}
}

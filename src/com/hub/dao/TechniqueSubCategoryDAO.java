package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.TechniqueCategory;
import com.hub.models.TechniqueSubCategory;
import com.hub.services.TechniqueService;

@Repository
public class TechniqueSubCategoryDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	TechniqueService techniqueService;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//Generates ID, don't use it.
	public boolean addTechniqueSubCategory(TechniqueSubCategory tsc){
		String SQL = "INSERT INTO tech_sub_category (name, description, category_id) VALUES (?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tsc.getName(), tsc.getDescription(), tsc.getCategoryId());	
		return rowsAffected == 1;
	}

	public TechniqueSubCategory getTechniqueSubCategoryById(long id){
		List<TechniqueSubCategory> results = new ArrayList<TechniqueSubCategory>();
		String SQL = "SELECT * FROM tech_sub_category WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);

		results = jdbcTemplate.query(SQL, TechniqueSubCategory.getRowMapper(), id);
		drillDown(results);
		return results.get(0);
	}

	public List<TechniqueSubCategory> getAllTechniqueSubCategories(){
		List<TechniqueSubCategory> results = new ArrayList<TechniqueSubCategory>();
		String SQL = "SELECT * FROM tech_sub_category";
		jdbcTemplate = new JdbcTemplate(dataSource);

		results = jdbcTemplate.query(SQL, TechniqueSubCategory.getRowMapper());
		drillDown(results);
		return results;
	}

	public List<TechniqueSubCategory> getAllTechniqueSubCategoriesInCategory(TechniqueCategory tc){
		List<TechniqueSubCategory> results = new ArrayList<TechniqueSubCategory>();
		String SQL = "SELECT * FROM tech_sub_category WHERE category_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);

		results = jdbcTemplate.query(SQL, TechniqueSubCategory.getRowMapper(), tc.getId());
		drillDown(results);
		return results;
	}

	public void drillDown(List<TechniqueSubCategory> toDrill){
		for(TechniqueSubCategory tsc: toDrill){
			tsc.setTechniques(techniqueService.getAllTechniquesInSubCategory(tsc));
		}
	}

	public boolean updateTechniqueSubCategory(TechniqueSubCategory tsc){
		String SQL = "UPDATE tech_sub_category SET name=?, description=?, category_id=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tsc.getName(), tsc.getDescription(), tsc.getCategoryId(), tsc.getId());
		return rowsAffected == 1;
	}

	public boolean deleteTechniqueSubCategory(TechniqueSubCategory tsc){
		String SQL = "DELETE FROM tech_sub_category WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);

		int rowsAffected = jdbcTemplate.update(SQL, tsc.getId());
		return rowsAffected == 1;
	}
}

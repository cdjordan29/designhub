package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class TechniqueSubCategory {

	private int id;
	private String name;
	private String description;
	private List<Technique> techniques;
	private int categoryId;
	private TechniqueCategory category;
	
	public TechniqueSubCategory(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public List<Technique> getTechniques() {
		return techniques;
	}
	public void setTechniques(List<Technique> techniques) {
		this.techniques = techniques;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public TechniqueCategory getCategory() {
		return category;
	}
	public void setCategory(TechniqueCategory category) {
		this.category = category;
		this.categoryId = category.getId();
	}

	public static RowMapper<TechniqueSubCategory> getRowMapper(){
		return new RowMapper<TechniqueSubCategory>(){
			public TechniqueSubCategory mapRow(ResultSet rs, int arg1) throws SQLException {
				TechniqueSubCategory tsc = new TechniqueSubCategory();
				tsc.setId(rs.getInt("id"));
				tsc.setName(rs.getString("name"));
				tsc.setDescription(rs.getString("description"));
				tsc.setCategoryId(rs.getInt("category_id"));
				return tsc;
			}
		};
	}
	
}

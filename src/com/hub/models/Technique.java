package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Technique {
	
	private int id;
	private String title;
	private String description;
	private int subCategoryId;
	private TechniqueSubCategory subCategory;
	
	public Technique(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public TechniqueSubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(TechniqueSubCategory subCategory) {
		this.subCategory = subCategory;
		this.subCategoryId = subCategory.getId();
	}

	public static RowMapper<Technique> getRowMapper(){
		return new RowMapper<Technique>(){
			public Technique mapRow(ResultSet rs, int arg1) throws SQLException {
				Technique t = new Technique();
				t.setId(rs.getInt("id"));
				t.setTitle(rs.getString("title"));
				t.setDescription(rs.getString("description"));
				t.setSubCategoryId(rs.getInt("sub_category_id"));
				return t;
			}
		};
	}
}

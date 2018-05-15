package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class TechniqueCategory {

	private int id;
	private String name;
	private List<TechniqueSubCategory> subCategories;
	
	public TechniqueCategory(){}

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
	public List<TechniqueSubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<TechniqueSubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	public static RowMapper<TechniqueCategory> getRowMapper(){
		return new RowMapper<TechniqueCategory>(){
			public TechniqueCategory mapRow(ResultSet rs, int arg1) throws SQLException {
				TechniqueCategory tc = new TechniqueCategory();
				tc.setId(rs.getInt("id"));
				tc.setName(rs.getString("name"));
				return tc;
			}	
		};
	}
}

package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RadfordRecommendedCollaboration {

	private int id;
	private String title;
	private String download;
	private String image;
	private String description;
	
	public RadfordRecommendedCollaboration(){}

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

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public static RowMapper<RadfordRecommendedCollaboration> getRowMapper(){
		return new RowMapper<RadfordRecommendedCollaboration>(){
			public RadfordRecommendedCollaboration mapRow(ResultSet rs, int rowNum) throws SQLException {
				RadfordRecommendedCollaboration rrc = new RadfordRecommendedCollaboration();
				rrc.setId(rs.getInt("id"));
				rrc.setTitle(rs.getString("title"));
				rrc.setDownload(rs.getString("download"));
				rrc.setImage(rs.getString("image"));
				rrc.setDescription(rs.getString("description"));
				return rrc;
			}
		};
	}
}

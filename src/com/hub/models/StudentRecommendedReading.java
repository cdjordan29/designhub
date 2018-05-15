package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRecommendedReading {

	private int id;
	private String title;
	private String buyLink;
	private String description;
	private String image;
	private int thumbsUp;
	private int thumbsDown;
	
	public StudentRecommendedReading() {}

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

	public String getBuyLink() {
		return buyLink;
	}

	public void setBuyLink(String buyLink) {
		this.buyLink = buyLink;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getThumbsUp() {
		return thumbsUp;
	}

	public void setThumbsUp(int thumbsUp) {
		this.thumbsUp = thumbsUp;
	}

	public int getThumbsDown() {
		return thumbsDown;
	}

	public void setThumbsDown(int thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
	
	public static RowMapper<StudentRecommendedReading> getRowMapper(){
		return new RowMapper<StudentRecommendedReading>(){
			public StudentRecommendedReading mapRow(ResultSet rs, int arg1) throws SQLException {
				StudentRecommendedReading srr = new StudentRecommendedReading();
				srr.setId(rs.getInt("id"));
				srr.setTitle(rs.getString("title"));
				srr.setBuyLink(rs.getString("buy_link"));
				srr.setImage(rs.getString("image"));
				srr.setDescription(rs.getString("description"));
				srr.setThumbsUp(rs.getInt("thumbs_up_count"));
				srr.setThumbsDown(rs.getInt("thumbs_down_count"));
				return srr;
			}
		};
	}
}

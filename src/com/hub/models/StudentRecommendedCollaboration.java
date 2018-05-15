package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRecommendedCollaboration {

	private int id;
	private String title;
	private String description;
	private String download;
	private String image;
	private int thumbsUp;
	private int thumbsDown;
	
	public StudentRecommendedCollaboration () {}

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
	
	public static RowMapper<StudentRecommendedCollaboration> getRowMapper(){
		return new RowMapper<StudentRecommendedCollaboration>(){
			public StudentRecommendedCollaboration mapRow(ResultSet rs, int arg1) throws SQLException {
				StudentRecommendedCollaboration src = new StudentRecommendedCollaboration();
				src.setId(rs.getInt("id"));
				src.setTitle(rs.getString("title"));
				src.setDescription(rs.getString("description"));
				src.setDownload(rs.getString("download"));
				src.setImage(rs.getString("image"));
				src.setThumbsUp(rs.getInt("thumbs_up_count"));
				src.setThumbsDown(rs.getInt("thumbs_down_count"));
				return src;
			}
		};
	}
}

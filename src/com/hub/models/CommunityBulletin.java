package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CommunityBulletin {
	
	private int id;
	private String description;
	private int thumbsUp;
	private int thumbsDown;
	
	public CommunityBulletin(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	};
	
	public static RowMapper<CommunityBulletin> getRowMapper(){
		return new RowMapper<CommunityBulletin>(){
			public CommunityBulletin mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommunityBulletin cb = new CommunityBulletin();
				cb.setId(rs.getInt("id"));
				cb.setDescription(rs.getString("description"));
				cb.setThumbsUp(rs.getInt("thumbs_up_count"));
				cb.setThumbsDown(rs.getInt("thumbs_down_count"));
				
				return cb;
			}
		};
	}
}

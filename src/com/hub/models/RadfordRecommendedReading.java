package com.hub.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RadfordRecommendedReading {

	private int id;
	private String title;
	private String download;
	private String buyLink;
	private String description;
	private String image;
	private int thumbsUp;
	private int thumbsDown;
	
	public RadfordRecommendedReading(){}

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
	
	public static RowMapper<RadfordRecommendedReading> getRowMapper(){
		return new RowMapper<RadfordRecommendedReading>(){
			public RadfordRecommendedReading mapRow(ResultSet rs, int rowNum) throws SQLException {
				RadfordRecommendedReading rrr = new RadfordRecommendedReading();
				rrr.setId(rs.getInt("id"));
				rrr.setTitle(rs.getString("title"));
				rrr.setDownload(rs.getString("download"));
				rrr.setBuyLink(rs.getString("buy_link"));
				rrr.setImage(rs.getString("image"));
				rrr.setDescription(rs.getString("description"));
				rrr.setThumbsUp(rs.getInt("thumbs_up_count"));
				rrr.setThumbsDown(rs.getInt("thumbs_down_count"));
				return rrr;
			}
		};
	}
}

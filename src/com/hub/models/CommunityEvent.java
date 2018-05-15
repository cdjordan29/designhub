package com.hub.models;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class CommunityEvent {

	private int id;
	private Date date;
	private String description;
	private byte[] blobAsBytes;
	
	public CommunityEvent(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getBlobAsBytes() {
		return blobAsBytes;
	}
	
	public void setBlobAsBytes(byte[] blobAsBytes) {
		this.blobAsBytes = blobAsBytes;
	}
	
	public static RowMapper<CommunityEvent> getRowMapper(){
		return new RowMapper<CommunityEvent>(){
			public CommunityEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommunityEvent ce = new CommunityEvent();
				ce.setId(rs.getInt("id"));
				ce.setDate(rs.getDate("date"));
				ce.setDescription(rs.getString("description"));
				ce.setBlobAsBytes(rs.getBytes("image"));
				
				return ce;
			}
		};
	}
}

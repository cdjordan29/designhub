package com.hub.models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.RowMapper;

public class Podcast {

	private int id;
	private String title;
	private String description;
	private String download;
	
	public Podcast(){}

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

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = convertToEmbeded(download);
	}
	
	private String convertToEmbeded(String youtubeUrl) {	
		String video_id="";
		if(youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {
			String expression = "^.*((youtu.be"+ "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*";
			CharSequence input = youtubeUrl;
			Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			if(matcher.matches()) {
				String groupIndex1 = matcher.group(7);
				if(groupIndex1!=null && groupIndex1.length()==11) {
					video_id = groupIndex1;
				}
			}
		 }
		 return "https://www.youtube.com/embed/" + video_id;
	}
	
	public static RowMapper<Podcast> getRowMapper(){
		return new RowMapper<Podcast>(){
			public Podcast mapRow(ResultSet rs, int rowNum) throws SQLException {
				Podcast p = new Podcast();
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setDescription(rs.getString("description"));
				p.setDownload(rs.getString("download"));
				return p;
			}
		};
	}
}

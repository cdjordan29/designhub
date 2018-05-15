package com.hub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hub.models.Podcast;

@Repository
public class PodcastDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public boolean addPodcast(Podcast p){
		String SQL = "INSERT INTO podcast (title, description, download) VALUES (?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, p.getTitle(), p.getDescription(), p.getDownload());
		
		return rowsAffected == 1;
	}
	
	public Podcast getPodcastById(long id){
		List<Podcast> results = new ArrayList<Podcast>();
		String SQL = "SELECT * FROM podcast WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Podcast.getRowMapper(), id);	
		return results.get(0);
	}
	
	public List<Podcast> getAllPodcasts(){
		List<Podcast> results = new ArrayList<Podcast>();
		String SQL = "SELECT * FROM podcast";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		results = jdbcTemplate.query(SQL, Podcast.getRowMapper());	
		return results;
	}
	
	public boolean updatePodcast(Podcast p){
		String SQL = "UPDATE podcast SET title=?, description=?, download=? where ID=?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, p.getTitle(), p.getDescription(), p.getDownload(), p.getId());
		return rowsAffected == 1;
	}
	
	public boolean deletePodcast(Podcast p){
		String SQL = "DELETE FROM podcast WHERE id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rowsAffected = jdbcTemplate.update(SQL, p.getId());
		return rowsAffected == 1;
	}
}

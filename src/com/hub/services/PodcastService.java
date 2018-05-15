package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.PodcastDAO;
import com.hub.models.Podcast;

@Service
public class PodcastService {

	@Autowired
	PodcastDAO dao;
	
	public boolean addPodcast(Podcast p){
		return dao.addPodcast(p);
	}
	
	public Podcast getPodcastById(int id){
		return dao.getPodcastById(id);
	}
	
	public List<Podcast> getAllPodcasts(){
		return dao.getAllPodcasts();
	}
	
	public boolean updatePodcast(Podcast p){
		return dao.updatePodcast(p);
	}
	
	public boolean deletePodcast(Podcast p){
		return dao.deletePodcast(p);
	}
}

package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.CommunityEventDAO;
import com.hub.models.CommunityEvent;

@Service
public class CommunityEventService {

	@Autowired
	CommunityEventDAO dao;
	
	public boolean addCommunityEvent(CommunityEvent ce){
		return dao.addCommunityEvent(ce);
	}
	
	public CommunityEvent getCommunityEventById(int id){
		return dao.getCommunityEventById(id);
	}
	
	public List<CommunityEvent> getAllCommunityEvents(){
		return dao.getAllCommunityEvents();
	}
	
	public boolean updateCommunityEvent(CommunityEvent ce){
		return dao.updateCommunityEvent(ce);
	}
	
	public boolean deleteCommunityEvent(CommunityEvent ce){
		return dao.deleteCommunityEvent(ce);
	}
}
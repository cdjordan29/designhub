package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.RadfordRecommendedCollaborationDAO;
import com.hub.models.RadfordRecommendedCollaboration;

@Service
public class RadfordRecommendedCollaborationService {

	@Autowired
	RadfordRecommendedCollaborationDAO dao;
	
	public boolean addRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		return dao.addRadfordRecommendedCollaboration(rrc);
	}
	
	public RadfordRecommendedCollaboration getRadfordRecommendedCollaborationById(int id){
		return dao.getRadfordRecommendedCollaborationById(id);
	}
	
	public List<RadfordRecommendedCollaboration> getAllRadfordRecommendedCollaborations(){
		return dao.getAllRadfordRecommendedCollaborations();
	}
	
	public boolean updateRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		return dao.updateRadfordRecommendedCollaboration(rrc);
	}
	
	public boolean deleteRadfordRecommendedCollaboration(RadfordRecommendedCollaboration rrc){
		return dao.deleteRadfordRecommendedCollaboration(rrc);
	}
}
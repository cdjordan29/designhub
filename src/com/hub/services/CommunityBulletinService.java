package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.CommunityBulletinDAO;
import com.hub.models.CommunityBulletin;

@Service
public class CommunityBulletinService {

	@Autowired
	CommunityBulletinDAO dao;
	
	public boolean addCommunityBulletin(CommunityBulletin cb){
		return dao.addCommunityBulletin(cb);
	}
	
	public CommunityBulletin getCommunityBulletinById(int id){
		return dao.getCommunityBulletinById(id);
	}
	
	public List<CommunityBulletin> getAllCommunityBulletins(){
		return dao.getAllCommunityBulletins();
	}
	
	public boolean updateCommunityBulletin(CommunityBulletin cb){
		return dao.updateCommunityBulletin(cb);
	}
	
	public boolean deleteCommunityBulletin(CommunityBulletin cb){
		return dao.deleteCommunityBulletin(cb);
	}
}

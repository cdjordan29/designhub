package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.GroupDAO;
import com.hub.models.Group;

@Service
public class GroupService {

	@Autowired
	GroupDAO dao;
	
	public boolean addGroup(Group g){
		return dao.addGroup(g);
	}
	
	public Group getGroupById(int id){
		return dao.getGroupById(id);
	}
	
	public List<Group> getAllGroups(){
		return dao.getAllGroups();
	}
	
	public boolean updateGroup(Group g){
		return dao.updateGroup(g);
	}
	
	public boolean deleteGroup(Group g){
		return dao.deleteGroup(g);
	}
}

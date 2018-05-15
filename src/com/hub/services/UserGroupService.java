package com.hub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.UserGroupDAO;
import com.hub.models.UserGroup;

@Service
public class UserGroupService {

	@Autowired
	UserGroupDAO dao;
	
	public UserGroup finderUserGroupById(long id) {
		return dao.findUserGroupById(id);
	}
}

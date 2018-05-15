package com.hub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ActiveUserService {
	
	@Autowired
	SessionRegistry sessionRegistry;
	

	public String getActiveUsersJson() throws JsonProcessingException {
		List<Object> users = sessionRegistry.getAllPrincipals().stream()
		.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
		.collect(Collectors.toList());
		    	
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(users);
	}
	
}

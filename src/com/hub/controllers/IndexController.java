package com.hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.models.User;
import com.hub.services.ActiveUserService;
import com.hub.services.UserService;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	ActiveUserService activeUserService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	User user = userService.findByUsername(email);
    	//Obscure password from client side
    	user.setPassword("");
    	ObjectMapper o = new ObjectMapper();
    	String userJson;
		try {
			userJson = o.writeValueAsString(user);
			model.addAttribute("user", userJson);
			model.addAttribute("activeUsers", activeUserService.getActiveUsersJson());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "index";
	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String accountPage(ModelMap model) {
		
		return indexPage(model);
	}
	
	
	
	
}

package com.hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.models.User;
import com.hub.services.ActiveUserService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	ActiveUserService activeUserService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String page(ModelMap model) {
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
		return "accountpage";
	}
	
	@RequestMapping(value = "/changePermissions", method = RequestMethod.POST)
	public String escalatePermissions(ModelMap model, @RequestParam(name = "email", required=true) String email,
			@RequestParam(name="permission level", required=true) String permissionLevl) {
		
		User toChange = userService.findByUsername(email);
		toChange.setPermission(permissionLevl);
		userService.updateUser(toChange);
		
		
		return "redirect:/account";
	}	
}
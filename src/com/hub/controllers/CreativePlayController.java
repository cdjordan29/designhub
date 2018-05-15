package com.hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hub.services.UserService;

@Controller
@RequestMapping("/creativeplay")
public class CreativePlayController {
	
	@Autowired
	UserService userService; //Can be used however you want

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		model.addAttribute("message", "Example of passing an attribute to the model");
		
		return "creativeplaypage";
	}	
}

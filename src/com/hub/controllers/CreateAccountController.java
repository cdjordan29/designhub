package com.hub.controllers;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hub.services.UserService;

@Controller
@RequestMapping("/createAccount")
public class CreateAccountController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String createAccountPage(ModelMap model){
		return "createaccount";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createAccount(ModelMap model, @RequestParam(name = "email", required=true) String email,
			@RequestParam(name = "password", required=true) String password, 
			@RequestParam(name = "passwordConfirm", required=true) String passwordConfirm,
			@RequestParam(name = "firstName", required=true) String firstName,
			@RequestParam(name = "lastName", required=true) String lastName){
		
		boolean success = false;
		if(validatePassword(password) && validateEmail(email) && validateName(firstName) && validateName(lastName) && password.equals(passwordConfirm)){
			success = userService.createUser(firstName, lastName, email, passwordConfirm, "student");
		} 
		
		if(success){
			return "login";
		}
		return "createaccount";
	}
	
	private boolean validatePassword(String password){
		if(password.length() >= 7 && password.length() <= 45){
			return true;
		}
		return false;
	}
	
	private boolean validateEmail(String email){
		return EmailValidator.getInstance().isValid(email);
	}
	
	private boolean validateName(String name){
		if(name.length() <= 45){
			return true;
		}
		return false;
	}
}

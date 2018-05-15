package com.hub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hub.dao.UserDAO;
import com.hub.models.User;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserDAO dao;
	
	public boolean updateUser(User u) {
		return dao.updateUser(u);
	}
	
	public User findUserById(long id){
		return dao.findUserById(id);
	}
	
	public User findByUsername(String email){
		return dao.findByUsername(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			return dao.findByUsername(email);
		} catch (Exception e){
			throw new UsernameNotFoundException(email);
		}
	}
	
	public boolean createUser(String firstName, String lastName, String email, String password, String permission){
		return dao.createUser(firstName, lastName, email, password, permission);
	}
}

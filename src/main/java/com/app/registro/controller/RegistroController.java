package com.app.registro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.registro.model.User;
import com.app.registro.service.RegistroService;
import java.util.List;
import antlr.collections.*;
import java.util.ArrayList;
@RestController

public class RegistroController {
	
	
	@Autowired
	private RegistroService service;
	
	
	@GetMapping("/getuserlist")
	@CrossOrigin(origins="http://localhost:4200")
	public List<User> fetchUserList(){
		List<User> users=new ArrayList<User>();
		users = service.fetchuserList();
		return users;
	}
	@GetMapping("getuserbyid/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public User fetchUserById(@PathVariable int id) {
	return	service.fetchUserByid(id).get();
		
	}
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId)) {
			 User userobj = service.fetchUserByEmailId(tempEmailId);
			if(userobj !=null) {
				throw new Exception("user with"+tempEmailId+"is already exist");
				
			}
			
		}
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
		
	}
	
	@PostMapping("login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		String tempPass = user.getPassword();
		User userObj=null;
		if(tempEmailId !=null && tempPass !=null) {
			
			userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
			
		}
		if(userObj == null ) {
			throw new Exception("bad credentials");
			
		}
		return userObj;
		
		}
	
	
}

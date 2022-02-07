package com.app.registro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.registro.model.User;
import com.app.registro.repository.RegistroRepository;

@Service
public class RegistroService {
		
		
	
	@Autowired
	private RegistroRepository repo;
	public List<User> fetchuserList(){
	return	repo.findAll();
		
	}
	public User saveUser (User user) {
	
		return repo.save(user);
		
	} 
	public Optional<User> fetchUserByid(int id){
		
	return repo.findById(id);	
		
	}
	public User fetchUserByEmailId(String email) {
		
		return repo.findByEmailId(email);
	}
	
public User fetchUserByEmailIdAndPassword(String email, String password) {
		
		return repo.findByEmailIdAndPassword(email, password);
	}

}

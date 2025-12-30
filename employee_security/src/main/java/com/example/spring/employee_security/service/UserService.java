package com.example.spring.employee_security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring.employee_security.entity.UserEntity;
import com.example.spring.employee_security.exception.ResourceNotFoundException;
import com.example.spring.employee_security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	// Add a new user
	public UserEntity add_newuser(UserEntity user){
		//Encoding the password
		user.setPassword(passwordencoder.encode(user.getPassword()));
		return userrepo.save(user);
	}
	
	// Get All user details
	public List<UserEntity> get_users(){
		return userrepo.findAll();
	}
	
	// Get EMployee details by id
	public UserEntity get_user_by_id(Long id) throws ResourceNotFoundException{
		return userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not Found"));
	}
	
	
	// update the employee by id
	public UserEntity update_emp(Long id, UserEntity userdata) {
		Optional<UserEntity> optional = userrepo.findById(id);
		if(optional.isPresent()) {
			UserEntity user = optional.get();
			user.setName(userdata.getName());
			user.setEmail(userdata.getEmail());
			user.setSalary(userdata.getSalary());
			
			return userrepo.save(user);
		}
		return userdata;		
	}
	
//	Delete the employee
	public String delete_emp(Long id) {
		userrepo.deleteById(id);
		return "User deleted in the db: "+id;
	}
}

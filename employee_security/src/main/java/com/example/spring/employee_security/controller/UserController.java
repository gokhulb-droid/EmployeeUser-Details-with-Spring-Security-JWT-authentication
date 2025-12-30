package com.example.spring.employee_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.employee_security.entity.UserEntity;
import com.example.spring.employee_security.exception.ResourceNotFoundException;
import com.example.spring.employee_security.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userservice;
	
//	Get all employee details
	@GetMapping
	public List<UserEntity> get_users(){
		return userservice.get_users();
	}

//	Add employee details
	@PostMapping
	public UserEntity add_newuser(@RequestBody UserEntity user) {
		return userservice.add_newuser(user);
	}
//	Get the employee by ID
	@GetMapping("/{id}")
	public UserEntity get_user_by_id(@PathVariable Long id) throws ResourceNotFoundException {
		return userservice.get_user_by_id(id);
	}
	
//	Update the employee details
	@PutMapping("/{id}")
	public UserEntity update_emp(@PathVariable Long id, @RequestBody UserEntity user){
		return userservice.update_emp(id, user);
	}
	
//	Delete the employee
	@DeleteMapping("/{id}")
	public String delete_emp(@PathVariable Long id) {
		return userservice.delete_emp(id);
	}
}

package com.example.spring.employee_security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="user_db")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String email;
	private double salary;
	private String username;
	private String password;
	
	public UserEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntity(Long id, String name, String email, double salary, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}

	
	

}

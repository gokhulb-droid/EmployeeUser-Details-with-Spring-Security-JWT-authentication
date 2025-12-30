package com.example.spring.employee_security.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
public class TestController {
	
	@GetMapping("/home")
	public String HomePage() {
		return "Welcome to Home Page";
	}

	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "Welcome to Dashboard page";
	}
	
	@GetMapping("/")
	public void redirectToUsers(HttpServletResponse response) throws IOException {
		response.sendRedirect("/api/users");
	}
}

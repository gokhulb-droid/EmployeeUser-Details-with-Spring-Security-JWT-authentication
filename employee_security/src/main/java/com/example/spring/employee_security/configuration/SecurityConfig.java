package com.example.spring.employee_security.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.spring.employee_security.jwt_security.JwtFilter;
import com.example.spring.employee_security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(authz -> 
			authz
			.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
			.requestMatchers("/").authenticated()
			.requestMatchers("/api/users/**").authenticated() // which url is should need to authenticate
			.anyRequest().permitAll() // which means any page is running will give permission no need of authentication
		)
//		Before the below code was not there, default login page not allowed due to authentication issue.
//		.formLogin( form -> form
//				.defaultSuccessUrl("/dashboard", true)
//				.permitAll()) // it allows default login page.
		.csrf(csrf -> csrf.disable())
		.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		;
		return http.build();
	}
	
//	// Creating In-Memory Users - used to test the username and password
//	@Bean
//	public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {
//		UserDetails user = User.withUsername("root")
//				.password(passwordEncoder.encode("root123"))
//				.roles("USER")
//				.build();
//		
//		UserDetails admin = User.withUsername("admin")
//				.password(passwordEncoder.encode("root_Admin123"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user, admin); //InMemoryUserDetailsManager class
//	}
	
	// Implementing DB user login - used to test the username and password
	@Bean
	public UserDetailsService userDetailService() {
		return new CustomUserDetailsService();
	}
	
	// This methods handles the authentication process for LOGIN
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());
		// authProvider.setUserDetailsService(userDetailService());
	     authProvider.setPasswordEncoder(passwordEncoder());           
	     return authProvider;
	 	}
	 
	// Password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(List.of(authenticationProvider()));
	}

}

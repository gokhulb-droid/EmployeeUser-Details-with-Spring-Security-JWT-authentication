package com.example.spring.employee_security.jwt_security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
/*	refer this website to get random key generation
 * https://acte.ltd/utils/randomkeygen
*/
	private static final String SECRET_KEY_STRING = "7eqWeit1W4FrTUHiEm6sFCulvUCP4U42";
	
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SECRET_KEY, Jwts.SIG.HS256)
				.compact();
		
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername());
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
		.verifyWith(SECRET_KEY)
		.build()
		.parseSignedClaims(token)
		.getPayload()
		.getSubject();
	}

}

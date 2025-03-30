package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.security.Key;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.JWTToken;
import com.example.demo.entity.User;
import com.example.demo.repository.JWTTokenRepository;
import com.example.demo.repository.UserRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {
	
	private final Key SIGNING_KEY;
	UserRepository userRepository;
	JWTTokenRepository jwtTokenRepository;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public AuthService( UserRepository userRepository, JWTTokenRepository jwtTokenRepository, @Value("${jwt.secret}") String jwtSecret) {
		this.userRepository = userRepository;
		this.jwtTokenRepository = jwtTokenRepository;
		this.SIGNING_KEY =   Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}


	public User authenticated(String username, String password) {
		
	Optional<User> existingUser = userRepository.findByUsername(username);
	
	if(existingUser.isPresent()) {
		User user = existingUser.get();
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		return user;
	}
	else {
		throw new RuntimeException("Invalid username");
	}
		
	}
	
	
	
	public String generateToken(User user) {
		String token;
		LocalDateTime currentTime = LocalDateTime.now();
		
		JWTToken existingToken = jwtTokenRepository.findByUserId(user.getUserId());
		
		if(existingToken != null && currentTime.isBefore(existingToken.getExpiresAt())) {
			token = existingToken.getToken();
		}
		else {
			token = generateNewToken(user);
			
			if(existingToken!=null) {
				jwtTokenRepository.delete(existingToken);
			}
			saveToken(user, token);
		}
		return token;
	}
	
	public String generateNewToken(User user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user.getUsername());
		builder.claim("role", user.getRole().name());
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(System.currentTimeMillis()+3600000));
		builder.signWith(SIGNING_KEY);
		String token = builder.compact();
		
		return token;
	}
	
	public void saveToken(User user, String token) {
		
		JWTToken jwtToken = new JWTToken(user, token, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		jwtTokenRepository.save(jwtToken);
	}
	
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SIGNING_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		
	}
	
	
	public boolean validateToken(String token) {
		
		System.out.println("Validating Token");
		try {
//		parse and validate token
			Jwts.parserBuilder()
			.setSigningKey(SIGNING_KEY)
			.build()
			.parseClaimsJws(token);
			
//			check if token is present in DB and is not expires
			 Optional<JWTToken> jwtToken = jwtTokenRepository.findByToken(token);
			
			if(jwtToken.isPresent()) {
				System.out.println("Token is present");
				return jwtToken.get().getExpiresAt().isAfter(LocalDateTime.now());
			}
			
			return false;
		}
		catch(Exception e) {
			System.out.println("Token Validation Failed " + e.getMessage());
			return false;
		}
		
		
		
	}
	
	
	
	
	//Logout functionality
	public void logout(User user) {
        int userId = user.getUserId();

        // Retrieve the JWT token associated with the user
        JWTToken token = jwtTokenRepository.findByUserId(userId);

        // If a token exists, delete it from the repository
        if (token != null) {
            jwtTokenRepository.deleteByUserId(userId);
        }
    }
	
	
	
	
	
	
}

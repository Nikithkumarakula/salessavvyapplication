package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	
	UserRepository userRepository;
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	public User register(User user) throws RuntimeException 
	{
		
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("Username already Present");
		}
		
		if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("Email already Registered");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		return userRepository.save(user);
	}
	
	
//	public Optional<User> getUserInfo(String username) {
//		Optional<User> user = userRepository.findByUsername(username);
//		
//		return user;
//	}
	
	public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found!"));
    }

}

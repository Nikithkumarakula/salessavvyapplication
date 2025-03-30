package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {
	
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registeredUser(@RequestBody User user) {
		
		try {
			
			User ruser=userService.register(user);
			return ResponseEntity.ok(Map.of("message","User registered Successfuly", "user", ruser));
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().body(Map.of("Error",e.getMessage()));
		}
		
	}
	
	
	@GetMapping("/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            
            return ResponseEntity.ok(Map.of("USERNAME", user.getUsername(), "USERID", user.getUserId(), "EMAIL", user.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found!");
        }
    }
	
	
	
	

}

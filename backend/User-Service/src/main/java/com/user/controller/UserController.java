package com.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user-api")
public class UserController {
	
	@Autowired
	public UserService service;
	
	@GetMapping("")
	public String test() {
		return "Welcome to User-Service API";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User u) {
		User user =service.registerUser(u);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User u) {
		return service.verifyUser(u);
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable UUID id) {
		return service.getUserById(id);
	}
	
}

package com.user.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repo.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	public IUserRepository repo;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

	public User registerUser(User user) {
		User existingUser = repo.findByUsername(user.getUsername());
		if (existingUser != null) return null;
	    user.setPassword(encoder.encode(user.getPassword()));
	    if (user.getRoles() == null) {
	        user.setRoles(new HashSet<>(Set.of("ROLE_USER")));
	    }
	    return repo.save(user);
	}
	
	public String verifyUser(User user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) 
			return jwtService.generateToken(repo.findByUsername(user.getUsername()));
		return "fail";
	}
	
	public User getUserById(UUID id) {
		Optional<User> op = repo.findById(id);
		return op.isEmpty() ? null : op.get();
	}
	
}

package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CustomerDTO;
import com.proxy.ICustomerProxy;

@RestController
@RequestMapping("/login-api")
public class LoginController {
    @Autowired
    private ICustomerProxy proxy;
    
    @GetMapping("") 
    public String test() {
    	return "Login API";
    }
    

	@PutMapping("/login") 
	public ResponseEntity<?> loginUser(@RequestBody CustomerDTO cDto) { 
		  CustomerDTO customer = proxy.isEmailExists(cDto.getEmail());
		
		  if (customer == null) 
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email Id!");
		  
		  if (!customer.getPassword().equals(cDto.getPassword()))
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password! Try again...");
		  CustomerDTO updateCustomer = proxy.userLogin(customer);
		  return ResponseEntity.ok(updateCustomer); 
	}
	
	@PutMapping("/logout") 
	public ResponseEntity<?> logoutUser(@RequestBody CustomerDTO cDto) { 
		  CustomerDTO updateCustomer = proxy.userLogout(cDto);
		  return ResponseEntity.ok(updateCustomer); 
	}
	
    @PostMapping("/register") 
    public ResponseEntity<?> registerUser(@RequestBody CustomerDTO cDto) {
        
    	CustomerDTO existingCustomerByEmail = proxy.isEmailExists(cDto.getEmail());
        if (existingCustomerByEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists!");
        }
        
        CustomerDTO existingCustomerByPhone = proxy.isPhoneExists(cDto.getPhone());
        if (existingCustomerByPhone != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone Number Already Exists!");
        }
        
        CustomerDTO customer = proxy.addCustomer(cDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(){
    	return ResponseEntity.ok("profile");
    }
    
}

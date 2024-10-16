package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.service.CustomerService;

@RestController
@RequestMapping("/customer-api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("")
    public String index() {
        return "This is Customer API\n"
                + "Available endpoints:\n"
                + "/customers - Retrieve a list of all customers\n"
                + "/customer/{id} - Retrieve customer details by customer ID\n"
                + "/add-customer - Add a new customer (send customer details in the request body)\n";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }
    
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return service.getCustomer(id);
    }
    
    @GetMapping("/customers/email/{email}")
    public Customer isEmailExists(@PathVariable String email) {
    	return service.getCustomerByEmail(email);
    }
    
    @GetMapping("/customers/phone/{phone}")
    public Customer iPhoneExists(@PathVariable String phone) {
    	return service.getCustomerByPhone(phone);
    }
    
    @PutMapping("/customer/login")
    public Customer customerLogin(@RequestBody Customer c) {
    	return service.updateCustomerLoginStatus(c);
    }
    
    @PutMapping("/customer/logout")
    public Customer customerLogout(@RequestBody Customer c) {
    	return service.updateCustomerLoginStatus(c);
    }

    @PostMapping("/add-customer")
    public Customer addCustomer(@RequestBody Customer c) {
        return service.createCustomer(c);
    }
}

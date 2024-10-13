package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.repo.ICustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepo repo;

    public Customer createCustomer(Customer c) {
    	Double d=Math.random();
    	String cid=c.getName().substring(0,2)+d.toString().substring(2,6);
		c.setUserId(cid);
        return repo.save(c);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }
    
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> op = repo.findByEmail(email);
        return op.isEmpty() ? null : op.get();
    }

    public Customer getCustomerByPhone(String phone) {
        Optional<Customer> op = repo.findByPhone(phone);
        return op.isEmpty() ? null : op.get();
    }

    public Customer getCustomer(String id) {
        Optional<Customer> op = repo.findById(id);
        return op.orElse(null);
    }
    
    public Customer updateCustomerLoginStatus(Customer c) {
    	Optional<Customer> op = repo.findByEmail(c.getEmail());
    	Customer updatedCustomer = op.get();
    	updatedCustomer.setLoginStatus(c.getLoginStatus()== 0 ? 1 : 0);
    	return repo.save(updatedCustomer);
    }
}

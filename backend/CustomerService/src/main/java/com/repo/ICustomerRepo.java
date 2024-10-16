package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Customer;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, String> {
	
	Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
}

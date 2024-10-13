package com.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dto.CustomerDTO;

@FeignClient(name="CustomerService", url="http://localhost:8096/customer-api")
public interface ICustomerProxy {
	
	@GetMapping("/customer-api/customer/{id}")
	CustomerDTO getCustomer(@PathVariable String id);
}

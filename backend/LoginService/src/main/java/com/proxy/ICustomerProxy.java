package com.proxy;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dto.CustomerDTO;

@FeignClient(name="CustomerService", url="http://localhost:8096")
public interface ICustomerProxy {
    
    @GetMapping("/customer-api/customer/{id}")
    CustomerDTO getCustomer(@PathVariable long id);
    
    @GetMapping("/customer-api/customers")
    List<CustomerDTO> getAllCustomers();
    
    @GetMapping("/customer-api/customers/email/{email}")
    CustomerDTO isEmailExists(@PathVariable String email);

    @GetMapping("/customer-api/customers/phone/{phone}")
    CustomerDTO isPhoneExists(@PathVariable String phone);
    
    @PutMapping("/customer-api/customer/login")
    CustomerDTO userLogin(@RequestBody CustomerDTO cDto);
    
    @PutMapping("/customer-api/customer/login")
    CustomerDTO userLogout(@RequestBody CustomerDTO cDto);
    
    @PostMapping("/customer-api/add-customer")
    CustomerDTO addCustomer(@RequestBody CustomerDTO cDto);
}

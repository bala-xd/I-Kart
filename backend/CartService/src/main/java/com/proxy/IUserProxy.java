package com.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dto.UserDTO;

@FeignClient(name="User-Service", url="http://localhost:8030")
public interface IUserProxy {
	
	@GetMapping("/user-api/user/{id}")
	UserDTO getUser(@PathVariable UUID id);
}

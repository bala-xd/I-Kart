package com.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dto.ProductDTO;

@FeignClient(name="ProductService", url="http://localhost:8091/product-api")
public interface IProductProxy {
	
	@GetMapping("/product/{id}")
	ProductDTO getProduct(@PathVariable long id);
}
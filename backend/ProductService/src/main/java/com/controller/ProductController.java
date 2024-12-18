package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/product-api")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("")
	public String index() {
	    return "This is Product API\n"
	            + "Available endpoints:\n"
	            + "/products - Retrieve a list of all products\n"
	            + "/product/{id} - Retrieve product details by product ID\n"
	            + "/add-product - Add a new product (send product details in the request body)\n";
	}
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable long id) {
		return service.getProduct(id);
	}
	
	@PostMapping("/add-product")
	public Product addProduct(@RequestBody Product p) {
		return service.createProduct(p);
	}
	
}

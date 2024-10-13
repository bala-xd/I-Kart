package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Product;
import com.repo.IProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private IProductRepo repo;
	
	public Product createProduct(Product p) {
		return repo.save(p);
	}
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	public Product getProduct(long id) {
		Optional<Product> op = repo.findById(id);
		return op.isEmpty() ? null : op.get();
	}
}

package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Product;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {

}

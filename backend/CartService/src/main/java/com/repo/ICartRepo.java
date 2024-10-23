package com.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Cart;

@Repository
public interface ICartRepo extends JpaRepository<Cart, UUID> {
}

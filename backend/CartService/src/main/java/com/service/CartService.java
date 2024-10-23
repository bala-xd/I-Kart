package com.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.ProductDTO;
import com.dto.UserDTO;
import com.model.Cart;
import com.model.CartItem;
import com.repo.ICartRepo;

@Service
public class CartService {

    @Autowired
    private ICartRepo repo;

    public Cart createCart(UserDTO uDto) {
    	Cart c = new Cart();
    	c.setCustomerName(uDto.getUsername());
    	c.setCartId(uDto.getId());
        return repo.save(c);
    }

    public Cart getCart(UUID cartId) {
        Optional<Cart> op = repo.findById(cartId);
        return op.orElse(null);
    }
    
    public Cart addItemtoCart(Cart cart, ProductDTO p, int qty) {
    	CartItem existingItem = cart.getItems().stream().filter(i -> i.getProductId()==p.getId()).findFirst().orElse(null);
    	System.out.println(existingItem);
    	
    	if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + qty);
        } else {
            CartItem newItem = new CartItem(p.getId(), p.getName(), qty, p.getPrice());
            cart.addItem(newItem);
        }
    	
    	
    	cart.setTotalPrice(calculateTotalPrice(cart));
    	return repo.save(cart);
    }
    
    public Cart removeItemFromCart(Cart cart, ProductDTO p) {
    	List<CartItem> items = cart.getItems().stream().filter(i -> i.getProductId()!=p.getId()).collect(Collectors.toList());
    	cart.setItems(items);
    	cart.setTotalPrice(calculateTotalPrice(cart));
    	return repo.save(cart);
    }
    
    public double calculateTotalPrice(Cart cart) {
    	double totalPrice = cart.getItems().stream()
    	        .mapToDouble(item -> item.getQuantity() * item.getPrice())
    	        .sum();
    	return totalPrice;
    }
}

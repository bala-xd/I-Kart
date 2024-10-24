package com.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.ProductDTO;
import com.model.Cart;
import com.model.CartItem;
import com.repo.ICartRepo;

@Service
public class CartService {

    @Autowired
    private ICartRepo repo;

    public Cart getCart(UUID cartId) {
        Optional<Cart> op = repo.findById(cartId);
        return op.orElse(new Cart(cartId));
    }
    
    public Cart addItemtoCart(Cart cart, ProductDTO p, int qty) {
    	CartItem existingItem = cart.getItems().stream().filter(i -> i.getProductId()==p.getId()).findFirst().orElse(null);
    	System.out.println(existingItem);
    	
    	if (existingItem != null) {
    		int newQty = existingItem.getQuantity()+qty;
    		
    		if (newQty > 0)	
    			existingItem.setQuantity(newQty);
    		else if (newQty == 0)	
    			removeItemFromCart(cart, p);
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

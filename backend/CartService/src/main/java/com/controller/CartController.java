package com.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProductDTO;
import com.dto.UserDTO;
import com.model.Cart;
import com.proxy.IProductProxy;
import com.proxy.IUserProxy;
import com.service.CartService;

@RestController
@RequestMapping("/cart-api")
public class CartController {

    @Autowired
    private CartService service;
    
    @Autowired
    private IUserProxy userProxy;
    
    @Autowired
    private IProductProxy productProxy;

    @GetMapping("")
    public String index() {
        return "This is Cart API\n"
                + "Available endpoints:\n"
                + "/cart/{cartId} - Retrieve the cart details by cart ID\n"
                + "/new-cart/{customerId} - Create a new cart for a customer by customer ID\n"
                + "/add-item/{cartId}/{productId}/{qty} - Add an item to the cart by cart ID, product ID, and quantity\n";
    }
    
    @GetMapping("/cart/{cartId}")
    public Cart getCart(@PathVariable UUID cartId) {
    	Cart cart = service.getCart(cartId);
		return (cart==null) ? createCartById(cartId) : cart;
	}

    @PostMapping("/add-item/{cartId}/{productId}/{qty}")
    public ResponseEntity<?> addCart(@PathVariable UUID cartId, 
    		@PathVariable long productId, @PathVariable int qty) {
    	
    	ProductDTO pDto = productProxy.getProduct(productId);
    	if (pDto==null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Product Id!");
    	
    	Cart cart = service.getCart(cartId);
    	if (cart==null)	cart = createCartById(cartId);
        
    	return ResponseEntity.ok(service.addItemtoCart(cart, pDto, qty));
    }
    
    @DeleteMapping("/delete-item/{cartId}/{productId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable UUID cartId, 
    		@PathVariable long productId) {
    	ProductDTO pDto = productProxy.getProduct(productId);
    	if (pDto==null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Product Id!");
    	Cart cart = service.getCart(cartId);
    	if (cart==null)	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Cart Id!");
        return ResponseEntity.ok(service.removeItemFromCart(cart, pDto));
    }
    
    public Cart createCartById(@PathVariable UUID cartId) {
    	UserDTO uDto = userProxy.getUser(cartId);
        return service.createCart(uDto);
    }
}

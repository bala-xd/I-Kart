package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CustomerDTO;
import com.dto.ProductDTO;
import com.model.Cart;
import com.proxy.ICustomerProxy;
import com.proxy.IProductProxy;
import com.service.CartService;

@RestController
@RequestMapping("/cart-api")
public class CartController {

    @Autowired
    private CartService service;
    
    @Autowired
    private ICustomerProxy c_proxy;
    
    @Autowired
    private IProductProxy p_proxy;

    @GetMapping("")
    public String index() {
        return "This is Cart API\n"
                + "Available endpoints:\n"
                + "/cart/{cartId} - Retrieve the cart details by cart ID\n"
                + "/new-cart/{customerId} - Create a new cart for a customer by customer ID\n"
                + "/add-item/{cartId}/{productId}/{qty} - Add an item to the cart by cart ID, product ID, and quantity\n";
    }
    
    @GetMapping("/cart/{cartId}")
    public Cart getCart(@PathVariable String cartId) {
		return service.getCart(cartId);
	}

    @PostMapping("/new-cart/{customerId}")
    public ResponseEntity<?> getCartById(@PathVariable String customerId) {
    	CustomerDTO cDto = c_proxy.getCustomer(customerId);
    	if (cDto==null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Customer Id!");
        return ResponseEntity.ok(service.createCart(cDto));
    }

    @PostMapping("/add-item/{cartId}/{productId}/{qty}")
    public ResponseEntity<?> addCart(@PathVariable String cartId, 
    		@PathVariable long productId, @PathVariable int qty) {
    	ProductDTO pDto = p_proxy.getProduct(productId);
    	if (pDto==null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Product Id!");
    	Cart cart = service.getCart(cartId);
    	if (cart==null)	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Cart Id!");
        return ResponseEntity.ok(service.addItemtoCart(cart, pDto, qty));
    }
    
    @DeleteMapping("/delete-item/{cartId}/{productId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable String cartId, 
    		@PathVariable long productId) {
    	ProductDTO pDto = p_proxy.getProduct(productId);
    	if (pDto==null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Product Id!");
    	Cart cart = service.getCart(cartId);
    	if (cart==null)	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Cart Id!");
        return ResponseEntity.ok(service.removeItemFromCart(cart, pDto));
    }
}

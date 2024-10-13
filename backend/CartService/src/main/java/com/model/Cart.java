package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
public class Cart {
	@Id
    private String cartId;
	
	private String customerName;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartItem> items;

    private double totalPrice;

    public Cart() {}

    public Cart(String cartId, List<CartItem> items, double totalPrice) {
        this.cartId = cartId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void addItem(CartItem item) {
    	items.add(item);
    }
}
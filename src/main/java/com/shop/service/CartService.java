package com.shop.service;

import com.shop.exception.CreateCartException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;

public interface CartService {
	
	public Cart prepareCart(String... args) throws CreateCartException, ShoppingCartException;
	
}

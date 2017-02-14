package com.shop.service;

import com.shop.exception.CartBillingException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;
import com.shop.model.Item;

public interface CartBillingService {
	
	public Cart generateBill(Cart cart) throws CartBillingException,ShoppingCartException;
	
	public Item calculatePrice(Item item)throws CartBillingException,ShoppingCartException;
	
	public Item applyDiscount(Item item, Cart iCart)throws CartBillingException,ShoppingCartException;
	
	public Cart calculateGrandTotals(Cart cart) throws CartBillingException,ShoppingCartException;

}

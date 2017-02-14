package com.shop.service;

import com.shop.exception.CartPromotionException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;

public interface PromotionService {
	
	public Cart associateOffersToCartItems(Cart cart) throws CartPromotionException, ShoppingCartException;

}

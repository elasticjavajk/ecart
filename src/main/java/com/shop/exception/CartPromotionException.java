package com.shop.exception;

public class CartPromotionException extends ShoppingCartException {

	
	private static final long serialVersionUID = 8232308741653799510L;

	public CartPromotionException(String message){
		super(message);
	}
	
	public CartPromotionException(Throwable cause){
		super(cause);
	}
	
	public CartPromotionException(String message, Throwable cause){
		super(message, cause);
	}

}

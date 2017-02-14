package com.shop.exception;

public class CartBillingException extends ShoppingCartException {

	
	private static final long serialVersionUID = 8464844024638107701L;

	public CartBillingException(String message){
		super(message);
	}
	
	public CartBillingException(Throwable cause){
		super(cause);
	}
	
	public CartBillingException(String message, Throwable cause){
		super(message, cause);
	}

}

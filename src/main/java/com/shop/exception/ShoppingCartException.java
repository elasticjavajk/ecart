package com.shop.exception;

public class ShoppingCartException extends Exception {

	private static final long serialVersionUID = -7382154490103008787L;

	public ShoppingCartException(String message){
		super(message);
	}
	
	public ShoppingCartException(Throwable cause){
		super(cause);
	}
	
	public ShoppingCartException(String message, Throwable cause){
		super(message, cause);
	}

}

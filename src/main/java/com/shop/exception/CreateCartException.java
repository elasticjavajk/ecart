package com.shop.exception;

public class CreateCartException extends ShoppingCartException {

	
	private static final long serialVersionUID = -2075151413499091520L;

	public CreateCartException(String message){
		super(message);
	}
	
	public CreateCartException(Throwable cause){
		super(cause);
	}
	
	public CreateCartException(String message, Throwable cause){
		super(message, cause);
	}

}

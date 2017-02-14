package com.shop.util;

import com.shop.model.Cart;
import com.shop.model.Item;

public class ShoppingCartUtil {
	
	public static boolean isNull(Object obj) {
	    return obj == null;
	}
	
	public static void printBill(Cart cart) {
		
		cart.getItems().stream().forEach(item -> print(item));
		print(cart);
	}

	static void print(Object obj){
		if(obj instanceof Item) {
			System.out.println(((Item)obj).toString());
		}else if(obj instanceof Cart){
			System.out.println(((Cart)obj).toString());
		}else{
			System.out.println("Thanks!");
		}
		
	}
}

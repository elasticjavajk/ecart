package com.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;
import com.shop.service.CartBillingService;
import com.shop.service.CartBillingServiceImpl;
import com.shop.service.CartService;
import com.shop.service.CartServiceImpl;
import com.shop.service.PromotionService;
import com.shop.service.PromotionServiceImpl;
import com.shop.util.ShoppingCartUtil;


/**
 * This is the main class for the ShoppingCartEngine, which manages adding items 
 * to cart, applying promotions, generating the bill and printing it to console.
 * @version 1.0
 * @since 1.0
 * @author jkumar
 * @see {@link CartServiceImpl.class}
 * @see {@link CartBillingServiceImpl.class}
 * @see {@link PromotionServiceImpl.class}
 *
 */
public class ShoppingCartEngine {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartEngine.class);
	/**
	 * To add items to cart and calculate the final bill, applying promotional offers
	 * @param args
	 * @throws ShoppingCartException 
	 * @{@link ThrowsException} 
	 */
	public static void main(String... args) {
		LOG.info("BEGIN - ShoppingCartEngine.billingEngine..");
		try{
			CartService cartService = new CartServiceImpl();
			PromotionService proService = new PromotionServiceImpl();
			CartBillingService priceService = new CartBillingServiceImpl();

			Cart cart = proService.associateOffersToCartItems(cartService.prepareCart(args));

			priceService.generateBill(cart);

			ShoppingCartUtil.printBill(cart);
			LOG.info("END - ShoppingCartEngine.billingEngine..");
			LOG.info("Thanks! Visit Again!!");
		}catch(ShoppingCartException e){
			LOG.error("Failed in Shopping Cart", e);
		}catch(Exception e){
			LOG.error("Failed with Exception in Shopping Cart", e);
		}


	}



}

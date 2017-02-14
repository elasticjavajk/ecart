package com.shop.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.exception.CartBillingException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;
import com.shop.model.Item;
import com.shop.util.ShoppingCartUtil;

/**
 * This class manages Cart Billing - calculate the individual Items billing, promotion applied
 * on Items, sub totals and grand totals for the cart
 * @version 1.0
 * @since 1.0
 * @author jkumar
 * @see {@link ShoppingCartException.class}
 * @see {@link CartBillingException.class}
 * @see {@link CartServiceImpl.class}
 * @see {@link PromotionServiceImpl.class}
 */
public class CartBillingServiceImpl implements CartBillingService {

	private static final Logger LOG = LoggerFactory.getLogger(CartBillingServiceImpl.class);
	Cart cart = new Cart();

	/**
	 * This mmethod generates the bill(total and discount amounts) for the cart provided
	 * @param cart
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * 
	 */
	@Override
	public Cart generateBill(Cart cart) throws ShoppingCartException{
		LOG.info("BEGIN - CartBillingServiceImpl.generateBill..");

		this.cart = cart;
		try{
			cart.getItems().forEach(item -> {
				try {
					calculatePrice(item);
				} catch (CartBillingException e) {
					LOG.error("Calculate prices in Billing Failed - ",e);				}
			});
			cart.getItems().forEach(item -> {
				try {
					applyDiscount(item, cart);
				} catch (CartBillingException e) {
					LOG.error("Apply Discounts for the Item Failed - ",e);		
				}
			});
			calculateGrandTotals(cart);
			LOG.info("END - CartBillingServiceImpl.generateBill..");

			return cart;
		}catch(Exception ex){
			LOG.error("Cart billing generation failed - ",ex);
			throw new ShoppingCartException("Cart billing generation failed - ",ex);
		}

	}

	/**
	 * This method calculates the item total price and sets in for the respective item in cart
	 * @param item
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * 
	 */
	@Override
	public Item calculatePrice(Item item) throws CartBillingException {
		item.setItemTotalPrice(new BigDecimal(item.getQty()).multiply(item.getUnitPrice()));
		return item;

	}

	/**
	 * This method calculates the item discount price and sets in for the respective item in cart
	 * @param item
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * 
	 */
	@Override
	public Item applyDiscount(Item item, Cart iCart) throws CartBillingException{
		if(item.getPromotions().stream().anyMatch(promotion -> ShoppingCartUtil.isNull(promotion.getConstraints()))){
			straightDiscount(item);
		}else{
			dependencyDiscount(item, iCart);
		}
		return item;

	}

	/**
	 * This method calculates the direct discount price and sets in for the respective item in cart
	 * @param item
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 * 
	 */
	private void straightDiscount(Item item) throws CartBillingException{
		if(!item.getPromotions().isEmpty()){
			double priceFraction = ((double)(item.getPromotions().get(0).getDiscountPercent()))/(double)(100);
			item.setItemDiscountAmount(new BigDecimal((priceFraction)*(item.getUnitPrice().doubleValue())*(item.getQty()), MathContext.DECIMAL64));
			
		}

	}

	/**
	 * This method calculates the discount based on the dependent item qty
	 * @param discItem
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 */
	private void dependencyDiscount(Item discItem, Cart iCart) throws CartBillingException{

		if(!discItem.getPromotions().isEmpty() && !ShoppingCartUtil.isNull(discItem.getPromotions().get(0).getConstraints())){
			String dItem = discItem.getPromotions().get(0).getConstraints().getDependsOn().toString();
			Item dependentItem = iCart.getItems().stream()
					.filter(item -> item.getItem().equalsIgnoreCase(dItem))
					.collect(Collectors.toList()).get(0);
			int discountEligibleQty = (int)(dependentItem.getQty() / discItem.getPromotions().get(0).getConstraints().getMinQty());
			int discountApplicableQty = (int) ((discountEligibleQty <= discItem.getQty()) ? discountEligibleQty : discItem.getQty());

			discItem.setItemDiscountAmount(new BigDecimal(discountApplicableQty).multiply((discItem.getUnitPrice().multiply((new BigDecimal(discItem.getPromotions().get(0).getDiscountPercent())
					.divide(new BigDecimal(100), 2))))));

		}


	}

	/**
	 * This method calculates the totals(sub totals, total discount, and grand totals and sets in for the respective cart
	 * @param item
	 * @throws {@linkCartBillingException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * 
	 */
	@Override
	public Cart calculateGrandTotals(Cart cart) throws CartBillingException{
		cart.setSubTotal(cart.getItems().stream().map(item -> item.getItemTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add));
		cart.setDiscountAmount(cart.getItems().stream().map(item -> item.getItemDiscountAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		cart.setGrandTotal(cart.getSubTotal().subtract(cart.getDiscountAmount()));

		return cart;


	}

}

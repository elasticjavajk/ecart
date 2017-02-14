package com.shop.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.config.ItemPriceCatalogue;
import com.shop.exception.CreateCartException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;
import com.shop.model.Item;

/**
 * This class manages Cart Creation - collating item quantities, adding Items to cart, updating Items with details
 * @version 1.0
 * @since 1.0
 * @author jkumar
 * @see {@link ShoppingCartException.class}
 * @see {@link CreateCartException.class}
 * @see {@link CartBillingServiceImpl.class}
 * @see {@link PromotionServiceImpl.class}
 */

public class CartServiceImpl implements CartService{
	private static final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);

	List<Item> itemsCatalogue = ItemPriceCatalogue.load();

	/**
	 * This method is to prepare the cart, with items recieved from the input arguments
	 * @param args
	 * @throws ShoppingCartException 
	 * @throws {@link CreateCartException}
	 * @throws {@link ShoppingCartException}
	 * @{@link ThrowsException}  
	 */
	@Override
	public Cart prepareCart(String... args) throws ShoppingCartException {
		LOG.info("BEGIN - CartServiceImpl.prepareCart..");

		try{
			if(args == null)
				throw new IllegalArgumentException("Invalid arguments Exception, args cannot be Null");

			List<Item> items = itemsCatalogue;
			Map<String, Long> pickedItems = collateItems(args);

			addQtyForItems(items, pickedItems);
			LOG.info("END - CartServiceImpl.prepareCart..");
			return addItemsToCart(items);
		}catch(IllegalArgumentException e){
			LOG.error("CartItems Illegal", e);
			throw new CreateCartException("CartItems Illegal", e);
		}catch(CreateCartException e){
			LOG.error("Cart creation failed - ", e);
			throw new CreateCartException("Cart Creation failed - ", e);
		}catch (Exception e){
			LOG.error("Prepare Shopping Cart Failed", e);
			throw new ShoppingCartException("Prepare Shopping Cart Failed", e);
		}
	}

	/**
	 * This mthod is to set the quantity for the items in cart
	 * @param items
	 * @param pickedItems
	 * @throws CreateCartException
	 */
	private void addQtyForItems(List<Item> items, Map<String,Long> pickedItems) throws CreateCartException{
		LOG.info("BEGIN - CartServiceImpl.addQtyForItems..");
		pickedItems.forEach((k,v) -> items.forEach(item -> {if(item.getItem().equalsIgnoreCase(k)) item.setQty(v);}));
		LOG.info("END - CartServiceImpl.addQtyForItems..");
		
	}

	/**
	 * This method is to collate the items from the items list added
	 * @param strings
	 * @throws CreateCartException
	 * @return
	 */
	private Map<String,Long> collateItems(String... strings) throws CreateCartException{
		LOG.info("BEGIN - CartServiceImpl.collateItems..");
		LOG.debug("Items picked - "+strings);
		return Arrays.asList(strings)
				.stream()
				.collect(
						Collectors.groupingBy(
								Function.identity(),
								Collectors.counting()));
		
	}

	/**
	 * This method is to add items to the Cart
	 * @param items
	 * @throws CreateCartException
	 * @return
	 */
	private Cart addItemsToCart(List<Item> items) throws CreateCartException{
		LOG.info("BEGIN - CartServiceImpl.addItemsToCart..");
		
		List<Item> cartItems = items.stream().filter(item -> item.getQty() != 0).collect(Collectors.toList());

		Cart cart = new Cart();
		cart.setItems(cartItems);
		LOG.info("END - CartServiceImpl.addItemsToCart..");
		return cart;
	}

}

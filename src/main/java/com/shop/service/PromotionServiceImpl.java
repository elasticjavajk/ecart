package com.shop.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.config.ItemPromotions;
import com.shop.exception.CartPromotionException;
import com.shop.exception.ShoppingCartException;
import com.shop.model.Cart;
import com.shop.model.Item;
import com.shop.model.PromotionOffer;
import com.shop.util.ShoppingCartUtil;

/**
 * This class manages Cart Promotions  - associating offers to Items, by validating promotions
 * @version 1.0
 * @since 1.0
 * @author jkumar
 * @see {@link ShoppingCartException.class}
 * @see {@link CartPromotionException.class}
 * @see {@link CartBillingServiceImpl.class}
 * @see {@link CartServiceImpl.class}
 */
public class PromotionServiceImpl implements PromotionService{
	private static final Logger LOG = LoggerFactory.getLogger(PromotionServiceImpl.class);

	List<PromotionOffer> promotionOffers = ItemPromotions.load();
	Cart cart = new Cart();

	/**
	 * This method associates offers for the items in cart provided
	 * @param cart
	 * @throws {@linkCartPromotionException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * @throws ShoppingCartException 
	 * 
	 */
	@Override
	public Cart associateOffersToCartItems(Cart cart) throws ShoppingCartException{
		LOG.info("BEGIN - PromotionServiceImpl.associateOffersToCartItems..");

		this.cart = cart;
		try{
		
			cart.getItems().forEach(item -> {
				try {
					this.associateOffers(item);
				} catch (CartPromotionException e) {
					LOG.error("Associate Item Offers failed - ",e);
				}
			});
			LOG.info("END - PromotionServiceImpl.associateOffersToCartItems..");

			return cart;
		}catch(Exception ex){
			throw new ShoppingCartException("Associate Offer Promotions with Items Failed - ",ex);
		}
		
	}

	/**
	 * This method associates offers for the items evaluating its validity
	 * @param item
	 * @throws {@linkCartPromotionException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 * @throws CartPromotionException 
	 * @throws ShoppingCartException 
	 * 
	 */
	private void associateOffers(Item item) throws CartPromotionException  {
		
			try {
				evaluate(item);
			} catch (CartPromotionException e) {
				LOG.error("Associate Offers to Promotions Failed - ",e);
				throw new CartPromotionException("Associate Offers to Promotions Failed - ",e);
			}
		
	}

	/**
	 * This method evaluates the promotion available for the item, on success associates offers for the items
	 * @param item
	 * @throws {@linkCartPromotionException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 */
	private void evaluate(Item item) throws CartPromotionException{
		LOG.info("BEGIN - PromotionServiceImpl.evaluate..");
		List<PromotionOffer> itemPromotionOffers = promotionOffers.stream().filter(offer -> offer.getPromotionOn().toString().equalsIgnoreCase(item.getItem())).collect(Collectors.toList());

		associatePromotionToItem(item, itemPromotionOffers);
		LOG.info("END - PromotionServiceImpl.evaluate..");
	}

	/**
	 * This method associates an available promotion to the item, checking the validity of the offer
	 * @param item
	 * @param itemPromotionOffers
	 * @throws {@linkCartPromotionException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 */
	private Item associatePromotionToItem(Item item, List<PromotionOffer> itemPromotionOffers) throws CartPromotionException{
		LOG.info("BEGIN - PromotionServiceImpl.associatePromotionToItem..");

		if(!ShoppingCartUtil.isNull(itemPromotionOffers) && !itemPromotionOffers.isEmpty()){
			List<PromotionOffer> filteredOffers = new ArrayList<>();
			if(isValid(itemPromotionOffers.get(0), item)){
				filteredOffers.add(itemPromotionOffers.get(0));
				item.setPromotions(filteredOffers); 
			}

		}
		LOG.info("END - PromotionServiceImpl.associatePromotionToItem..");

		return item;
	}

	/**
	 * This method checks the validity of an available promotion and returns a boolean
	 * @param srcItem
	 * @param promotionOffer
	 * @throws {@linkCartPromotionException#}
	 * @throws {@link ShoppingCartException#}
	 * @return
	 */
	private boolean isValid(PromotionOffer promotionOffer, Item srcItem) throws CartPromotionException{
		LOG.info("BEGIN - PromotionServiceImpl.isValid..");

		boolean isValid = false;
		if((promotionOffer.getPromotionOn().toString().equalsIgnoreCase(srcItem.getItem()))){
			if(promotionOffer.isTimeBound() && LocalDate.now().isBefore(promotionOffer.getValidUntil())){
				isValid = true;
			}

			if(!ShoppingCartUtil.isNull(promotionOffer.getConstraints())){
				isValid = cart.getItems().stream().anyMatch(item -> (item.getItem().equalsIgnoreCase(promotionOffer.getConstraints().getDependsOn().toString()) && (promotionOffer.getConstraints().getMinQty() <= item.getQty())));
			}
		}
		LOG.info("END - PromotionServiceImpl.isValid..");

		return isValid;
	}

}

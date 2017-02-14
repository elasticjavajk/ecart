package com.shop.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.model.Constraint;
import com.shop.model.PromotionOffer;
import com.shop.model.PromotionType;
import com.shop.model.SKU;

/**
 * This class contains the Item Promotions map Object with promotions and items mapping details
 * @version 1.0
 * @since 1.0
 * @author jvvss
 *
 */
public class ItemPromotions {
	private static final Logger LOG = LoggerFactory.getLogger(ItemPromotions.class);
	public static List<PromotionOffer> load() {
		LOG.info("Loading Promotions Map from - ItemPromotions.load..");
		return Arrays.asList(
				//new PromotionOffer(promotionId, promotion, isTimeBound, validUntil, description, promotionOn, promotionType, discountPercent, constraints)
				new PromotionOffer("P001","10Percent",true, LocalDate.parse("2017-02-19"), "Apples 10% Off", SKU.Apples, PromotionType.discount, 10.0, null),
				new PromotionOffer("P002","SoupBread",false, null, "Bread 50% Off on Soups 2:1",SKU.Bread, PromotionType.discount, 50.0,new Constraint(SKU.Soup, 2))
				);
	}

}

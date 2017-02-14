package com.shop.config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.ShoppingCartEngine;
import com.shop.model.Item;

/**
 * This class contains the Catalogue Object with Items and Prices
 * @version 1.0
 * @since 1.0
 * @author jvvss
 *
 */
public class ItemPriceCatalogue {
	private static final Logger LOG = LoggerFactory.getLogger(ItemPriceCatalogue.class);
	public static List<Item> load() {
		LOG.info("Loading ItemPrices from - ItemPriceCatalogue.load..");
		return Arrays.asList(
				new Item("SKU001","Soup",new BigDecimal("0.65")),
				new Item("SKU002","Bread",new BigDecimal("0.80")),
				new Item("SKU001","Milk",new BigDecimal("1.30")),
				new Item("SKU001","Apples",new BigDecimal("1.00"))
				);
	}
}

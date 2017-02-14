package com.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable{

	private static final long serialVersionUID = -996381801118883104L;
	private String skuId;
	private String item;
	private long qty;
	private BigDecimal unitPrice = BigDecimal.ZERO;
	private List<PromotionOffer> promotions = new ArrayList<>();
	private BigDecimal itemDiscountAmount = BigDecimal.ZERO;
	private BigDecimal itemTotalPrice = BigDecimal.ZERO;


	public Item(String item, long qty){
		this.item  = item;
		this.qty = qty;
	}

	public Item(String skuId, String item, BigDecimal unitPrice ){
		this.skuId  = skuId;
		this.item  = item;
		this.unitPrice = unitPrice;
	}


	public Item(String skuId, String item, long qty, BigDecimal unitPrice ){
		this.skuId  = skuId;
		this.item  = item;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<PromotionOffer> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionOffer> promotions) {
		this.promotions = promotions;
	}

	public BigDecimal getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(BigDecimal itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public BigDecimal getItemDiscountAmount() {
		return itemDiscountAmount;
	}

	public void setItemDiscountAmount(BigDecimal itemDiscountAmount) {
		this.itemDiscountAmount = itemDiscountAmount;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		String desc = promotions.isEmpty()? "No Offer Associated " : promotions.get(0).getDescription();
		return sb.append(skuId+" - "+item+" - "+qty+" : "+itemTotalPrice+"\r\n").append(desc).append(" - "+itemDiscountAmount).toString();
	}







}

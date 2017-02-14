package com.shop.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class contains the promotion offer Object
 * @author jvvss
 *
 */
public class PromotionOffer implements Serializable{
	
	private static final long serialVersionUID = 4569744238148014762L;
	private String promotionId;
	private String promotion;
	private boolean isTimeBound;
	private LocalDate validUntil;
	private String description;
	private PromotionType promotionType;
	private Constraint constraints;
	private SKU promotionOn;
	private double discountPercent;
	
	public PromotionOffer(String promotionId, String promotion, boolean isTimeBound, LocalDate validUntil, String description, SKU promotionOn, PromotionType promotionType, double discountPercent, Constraint constraints){
		this.promotionId = promotionId;
		this.promotion = promotion;
		this.promotionOn = promotionOn;
		this.isTimeBound = isTimeBound;
		this.validUntil = validUntil;
		this.description = description;
		this.promotionType = promotionType;
		this.setDiscountPercent(discountPercent);
		this.constraints = constraints;
	}
	
	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}


	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public boolean isTimeBound() {
		return isTimeBound;
	}

	public void setTimeBound(boolean isTimeBound) {
		this.isTimeBound = isTimeBound;
	}

	public LocalDate getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDate validUntil) {
		this.validUntil = validUntil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public SKU getPromotionOn() {
		return promotionOn;
	}

	public void setPromotionOn(SKU promotionOn) {
		this.promotionOn = promotionOn;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Constraint getConstraints() {
		return constraints;
	}

	public void setConstraints(Constraint constraints) {
		this.constraints = constraints;
	}

	public String toString(){
		return promotion+" : "+description.toString();
	}

}

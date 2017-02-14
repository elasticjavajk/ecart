package com.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
	
	private static final long serialVersionUID = 3053709444656115045L;
	private List<Item> items = new ArrayList<Item>();
	private BigDecimal subTotal = BigDecimal.ZERO;
	private BigDecimal discountAmount = BigDecimal.ZERO;
	private BigDecimal grandTotal = BigDecimal.ZERO;
	
	public Cart(){
		
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public void addItem(Item item){
		getItems().add(new Item(item.getSkuId(), item.getItem(), item.getUnitPrice()));
	}
	
	public String toString(){
		return "=====================================\r\n"+"Sub Total : £"+subTotal+"\r\n"+"Total Discount : £"+discountAmount+"\r\n"+"=====================================\r\n"+"Grand Total : £"+grandTotal.toString();
	}

}

package com.shop.model;

import java.io.Serializable;

public class Constraint implements Serializable{
	
	private static final long serialVersionUID = 5214146372303877509L;
	private SKU dependsOn;
	private long minQty;
	
	public Constraint (SKU soup, long minQty){
		this.dependsOn = soup;
		this.minQty = minQty;
	}

	public SKU getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(SKU dependsOn) {
		this.dependsOn = dependsOn;
	}

	public long getMinQty() {
		return minQty;
	}

	public void setMinQty(long minQty) {
		this.minQty = minQty;
	}
	
	public String toString(){
		return dependsOn+" - "+minQty+"".toString();
	}
	

}

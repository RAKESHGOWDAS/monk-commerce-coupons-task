package com.monkcommerce.coupon.processor.dto;

public class DiscountedCartItemDTO {

	private String productName;
	private double originalPrice;
	private double discountedPrice;
	private int quantity;

	public DiscountedCartItemDTO() {

	}

	public DiscountedCartItemDTO(String productName, double originalPrice, double discountedPrice, int quantity) {
		super();
		this.productName = productName;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

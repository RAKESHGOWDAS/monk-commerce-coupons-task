package com.monkcommerce.coupon.processor.dto;

public class CartItemDTO {

	private String productName;
	private double price;
	private int quantity;

	public CartItemDTO() {

	}

	public CartItemDTO(String productName, double price, int quantity) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

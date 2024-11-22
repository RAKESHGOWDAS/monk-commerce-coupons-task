package com.monkcommerce.coupon.processor.dto;

import java.util.List;

public class DiscountedCartDTO {
	private List<DiscountedCartItemDTO> items;
	private double totalDiscount;

	public DiscountedCartDTO() {

	}

	public DiscountedCartDTO(List<DiscountedCartItemDTO> items, double totalDiscount) {
		super();
		this.items = items;
		this.totalDiscount = totalDiscount;
	}

	public List<DiscountedCartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<DiscountedCartItemDTO> items) {
		this.items = items;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

}

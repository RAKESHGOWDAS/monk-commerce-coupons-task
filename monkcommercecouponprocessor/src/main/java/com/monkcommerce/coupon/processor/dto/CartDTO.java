package com.monkcommerce.coupon.processor.dto;

import java.util.List;

public class CartDTO {

	private List<CartItemDTO> items;

	public CartDTO() {
	}

	public CartDTO(List<CartItemDTO> items) {
		super();
		this.items = items;
	}

	public List<CartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}

}

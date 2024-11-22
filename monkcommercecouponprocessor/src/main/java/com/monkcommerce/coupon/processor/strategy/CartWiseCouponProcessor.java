package com.monkcommerce.coupon.processor.strategy;

import java.util.List;
import java.util.stream.Collectors;

import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartItemDTO;

public class CartWiseCouponProcessor implements CouponProcessor {

	@Override
	public DiscountedCartDTO applyCoupon(CartDTO cart, double discount) {
		double totalCartValue = cart.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity())
				.sum();
		double totalDiscount = totalCartValue * (discount / 100);

		List<DiscountedCartItemDTO> discountedItems = cart.getItems().stream()
				.map(item -> new DiscountedCartItemDTO(item.getProductName(), item.getPrice(),
						item.getPrice() - (item.getPrice() * (discount / 100)), item.getQuantity()))
				.collect(Collectors.toList());

		return new DiscountedCartDTO(discountedItems, totalDiscount);
	}
}

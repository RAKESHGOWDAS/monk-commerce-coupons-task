package com.monkcommerce.coupon.processor.strategy;

import java.util.List;
import java.util.stream.Collectors;

import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartItemDTO;

public class ProductWiseCouponProcessor implements CouponProcessor {

	@Override
	public DiscountedCartDTO applyCoupon(CartDTO cart, double discount) {
		List<DiscountedCartItemDTO> discountedItems = cart.getItems().stream()
				.map(item -> new DiscountedCartItemDTO(item.getProductName(), item.getPrice(),
						item.getPrice() - (item.getPrice() * (discount / 100)), item.getQuantity()))
				.collect(Collectors.toList());

		double totalDiscount = discountedItems.stream()
				.mapToDouble(item -> item.getDiscountedPrice() * item.getQuantity()).sum();

		return new DiscountedCartDTO(discountedItems, totalDiscount);
	}
}

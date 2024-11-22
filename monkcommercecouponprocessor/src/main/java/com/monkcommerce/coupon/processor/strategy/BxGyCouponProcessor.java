package com.monkcommerce.coupon.processor.strategy;

import java.util.ArrayList;

import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;

public class BxGyCouponProcessor implements CouponProcessor {

	@Override
	public DiscountedCartDTO applyCoupon(CartDTO cart, double discount) {
		// we can Implement Buy X Get Y logic based on business requirement
		return new DiscountedCartDTO(new ArrayList(), 0.0);
	}
}

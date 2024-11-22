package com.monkcommerce.coupon.processor.strategy;

import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;

public interface CouponProcessor {

	DiscountedCartDTO applyCoupon(CartDTO cart, double discount);

}

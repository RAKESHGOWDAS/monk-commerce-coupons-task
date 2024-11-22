package com.monkcommerce.coupon.processor.strategy;

public class CouponProcessorFactory {

	public static CouponProcessor getProcessor(String couponType) {
		switch (couponType.toUpperCase()) {
		case "CART_WISE":
			return new CartWiseCouponProcessor();
		case "PRODUCT_WISE":
			return new ProductWiseCouponProcessor();
		case "BXGY":
			return new BxGyCouponProcessor();
		default:
			throw new IllegalArgumentException("Invalid coupon type: " + couponType);
		}
	}
}

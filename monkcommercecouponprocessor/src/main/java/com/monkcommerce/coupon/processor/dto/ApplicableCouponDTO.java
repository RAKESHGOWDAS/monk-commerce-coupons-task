package com.monkcommerce.coupon.processor.dto;

public class ApplicableCouponDTO {

	private String couponCode;
	private double discount;

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}

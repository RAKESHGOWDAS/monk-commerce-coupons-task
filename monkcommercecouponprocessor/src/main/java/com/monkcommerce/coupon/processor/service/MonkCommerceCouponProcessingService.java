package com.monkcommerce.coupon.processor.service;

import java.util.List;

import com.monkcommerce.coupon.processor.dto.ApplicableCouponDTO;
import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.CouponDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;

public interface MonkCommerceCouponProcessingService {

	public CouponDTO createCoupon(CouponDTO couponDTO);

	public List<CouponDTO> getAllCoupons();

	public CouponDTO getCouponById(Integer id);

	public CouponDTO updateCoupon(Integer id, CouponDTO couponDTO);

	public void deleteCoupon(Integer id);

	public List<ApplicableCouponDTO> fetchApplicableCoupons(CartDTO cartDTO);

	public DiscountedCartDTO applyCouponToCart(Integer id, CartDTO cartDTO);

}

package com.monkcommerce.coupon.processor.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monkcommerce.coupon.processor.dto.ApplicableCouponDTO;
import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.CouponDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;
import com.monkcommerce.coupon.processor.service.MonkCommerceCouponProcessingService;

@RestController
@RequestMapping(value = "/api")
public class MonkCommerceCouponProcessingController {

	private MonkCommerceCouponProcessingService couponService = null;

	@Autowired
	public MonkCommerceCouponProcessingController(MonkCommerceCouponProcessingService couponService) {
		this.couponService = couponService;
	}

	@PostMapping(value = "/coupons")
	public ResponseEntity<CouponDTO> createCoupon(@RequestBody @Valid CouponDTO couponDTO) {
		CouponDTO createdCoupon = couponService.createCoupon(couponDTO);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body(createdCoupon);
	}

	@GetMapping(value = "/coupons")
	public ResponseEntity<?> getCoupons(@RequestParam(required = false) Integer id) {
		if (id == null) {
			List<CouponDTO> coupon = couponService.getAllCoupons();
			return ResponseEntity.ok(coupon);
		} else {
			CouponDTO coupons = couponService.getCouponById(id);
			return ResponseEntity.ok(coupons);
		}
	}

	@PutMapping(value = "/coupons/{id}")
	public ResponseEntity<CouponDTO> updateCoupon(@PathVariable Integer id, @RequestBody @Valid CouponDTO couponDTO) {
		CouponDTO updatedCoupon = couponService.updateCoupon(id, couponDTO);
		return ResponseEntity.ok(updatedCoupon);
	}

	@DeleteMapping(value = "/coupons/{id}")
	public ResponseEntity<String> deleteCoupon(@PathVariable Integer id) {
		couponService.deleteCoupon(id);
		return ResponseEntity.ok("Coupon with ID " + id + " has been deleted successfully.");
	}

	@PostMapping(value = "/applicable-coupons")
	public ResponseEntity<List<ApplicableCouponDTO>> fetchApplicableCoupons(@RequestBody @Valid CartDTO cartDTO) {
		List<ApplicableCouponDTO> applicableCoupons = couponService.fetchApplicableCoupons(cartDTO);
		return ResponseEntity.ok(applicableCoupons);
	}

	@PostMapping(value = "/apply-coupon/{id}")
	public ResponseEntity<DiscountedCartDTO> applyCouponToCart(@PathVariable Integer id,
			@RequestBody @Valid CartDTO cartDTO) {
		DiscountedCartDTO discountedCart = couponService.applyCouponToCart(id, cartDTO);
		return ResponseEntity.ok(discountedCart);
	}

}

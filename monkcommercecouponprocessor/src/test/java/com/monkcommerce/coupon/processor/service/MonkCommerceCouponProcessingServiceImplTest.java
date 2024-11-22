package com.monkcommerce.coupon.processor.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.monkcommerce.coupon.processor.dto.CouponDTO;
import com.monkcommerce.coupon.processor.entity.Coupon;
import com.monkcommerce.coupon.processor.repository.CouponRepository;

@RunWith(MockitoJUnitRunner.class)
public class MonkCommerceCouponProcessingServiceImplTest {

	@Mock
	private CouponRepository couponRepository;

	@InjectMocks
	private MonkCommerceCouponProcessingServiceImpl couponService;

	@Test
	public void testCreateCoupon_whenSuccessful() {
		// Given
		CouponDTO couponDTO = new CouponDTO("WELCOME10", 10.0, LocalDate.parse("2024-12-31"), null); // `type` is null
		Coupon coupon = new Coupon("WELCOME10", 10.0, LocalDate.parse("2024-12-31"));
		coupon.setType("CART_WISE"); // Set the default `type` manually in the mocked object

		when(couponRepository.findByCode("WELCOME10")).thenReturn(Optional.empty());
		when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

		// When
		CouponDTO result = couponService.createCoupon(couponDTO);

		// Then
		assertEquals("WELCOME10", result.getCode());
		assertEquals(10.0, result.getDiscount(), 0.0);
		assertEquals(LocalDate.parse("2024-12-31"), result.getExpiryDate());
		assertEquals("CART_WISE", result.getType()); // Assert the default type
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCoupon_whenDuplicate_thenThrowException() {
		CouponDTO couponDTO = new CouponDTO("WELCOME10", 10.0, LocalDate.parse("2024-12-31"), null);
		when(couponRepository.findByCode("WELCOME10")).thenReturn(Optional.of(new Coupon()));

		couponService.createCoupon(couponDTO);
	}

	@Test
	public void testGetAllCoupons_returnsList() {
		Coupon coupon1 = new Coupon("WELCOME10", 10.0, LocalDate.parse("2024-12-31"));
		Coupon coupon2 = new Coupon("DISCOUNT20", 20.0, LocalDate.parse("2025-01-31"));
		when(couponRepository.findAll()).thenReturn(Arrays.asList(coupon1, coupon2));

		List<CouponDTO> result = couponService.getAllCoupons();

		assertEquals(2, result.size());
		assertEquals("WELCOME10", result.get(0).getCode());
		assertEquals("DISCOUNT20", result.get(1).getCode());
	}
}

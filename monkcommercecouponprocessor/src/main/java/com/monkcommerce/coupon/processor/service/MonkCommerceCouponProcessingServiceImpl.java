package com.monkcommerce.coupon.processor.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkcommerce.coupon.processor.dto.ApplicableCouponDTO;
import com.monkcommerce.coupon.processor.dto.CartDTO;
import com.monkcommerce.coupon.processor.dto.CouponDTO;
import com.monkcommerce.coupon.processor.dto.DiscountedCartDTO;
import com.monkcommerce.coupon.processor.entity.Coupon;
import com.monkcommerce.coupon.processor.repository.CouponRepository;
import com.monkcommerce.coupon.processor.strategy.CouponProcessor;
import com.monkcommerce.coupon.processor.strategy.CouponProcessorFactory;

@Service
public class MonkCommerceCouponProcessingServiceImpl implements MonkCommerceCouponProcessingService {

	private final CouponRepository couponRepository;

	@Autowired
	public MonkCommerceCouponProcessingServiceImpl(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	@Override
	public CouponDTO createCoupon(CouponDTO couponDTO) {

		try {
			// Check if the coupon code already exists
			if (couponRepository.findByCode(couponDTO.getCode()).isPresent()) {
				throw new IllegalArgumentException("Coupon code already exists");
			}

			Coupon coupon = new Coupon(couponDTO.getCode(), couponDTO.getDiscount(), couponDTO.getExpiryDate());
			coupon.setType(couponDTO.getType() == null || couponDTO.getType().trim().isEmpty() ? "CART_WISE"
					: couponDTO.getType());
			LocalDate now = LocalDate.now();
			coupon.setCreatedAt(now);
			coupon.setUpdatedAt(now);

			Coupon savedCoupon = couponRepository.save(coupon);

			CouponDTO savedCouponDTO = new CouponDTO(savedCoupon.getCode(), savedCoupon.getDiscount(),
					savedCoupon.getExpiryDate(), savedCoupon.getType());
			return savedCouponDTO;

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Failed to create coupon: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while creating the coupon: " + e.getMessage(), e);
		}
	}

	@Override
	public List<CouponDTO> getAllCoupons() {
		try {
			return couponRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while fetching all coupons: " + e.getMessage(), e);
		}
	}

	@Override
	public CouponDTO getCouponById(Integer id) {
		try {
			Coupon coupon = couponRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Coupon with ID " + id + " not found"));
			return mapToDTO(coupon);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Failed to fetch coupon: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while fetching the coupon: " + e.getMessage(), e);
		}
	}

	private CouponDTO mapToDTO(Coupon coupon) {
		CouponDTO dto = new CouponDTO(coupon.getCode(), coupon.getDiscount(), coupon.getExpiryDate(), coupon.getType());
		return dto;
	}

	@Override
	public CouponDTO updateCoupon(Integer id, CouponDTO couponDTO) {
		try {
			Coupon existingCoupon = couponRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Coupon with ID " + id + " not found"));

			// Update the fields
			existingCoupon.setCode(couponDTO.getCode());
			existingCoupon.setDiscount(couponDTO.getDiscount());
			existingCoupon.setExpiryDate(couponDTO.getExpiryDate());
			existingCoupon.setType(couponDTO.getType());
			existingCoupon.setUpdatedAt(LocalDate.now());

			// Save the updated coupon
			Coupon updatedCoupon = couponRepository.save(existingCoupon);

			// Convert to DTO
			return mapToDTO(updatedCoupon);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Failed to update coupon: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while updating the coupon: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteCoupon(Integer id) {
		try {
			// Check if the coupon exists
			Coupon existingCoupon = couponRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Coupon with ID " + id + " not found"));

			// Delete the coupon
			couponRepository.delete(existingCoupon);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Failed to delete coupon: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred while deleting the coupon: " + e.getMessage(), e);
		}
	}

	@Override
	public List<ApplicableCouponDTO> fetchApplicableCoupons(CartDTO cartDTO) {
		try {
			List<Coupon> allCoupons = couponRepository.findAll();
			List<ApplicableCouponDTO> applicableCoupons = new ArrayList<>();

			for (Coupon coupon : allCoupons) {
				// Convert Date to LocalDate
				LocalDate expiryDate = coupon.getExpiryDate();

				// Check if the coupon is expired
				if (expiryDate.isBefore(LocalDate.now())) {
					continue;
				}

				// Calculate total discount for this coupon
				double totalCartValue = cartDTO.getItems().stream()
						.mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

				double discount = totalCartValue * (coupon.getDiscount() / 100);

				// Add applicable coupon to the result
				ApplicableCouponDTO applicableCouponDTO = new ApplicableCouponDTO();
				applicableCouponDTO.setCouponCode(coupon.getCode());
				applicableCouponDTO.setDiscount(discount);
				applicableCoupons.add(applicableCouponDTO);
			}

			return applicableCoupons;

		} catch (Exception e) {
			throw new RuntimeException("An error occurred while fetching applicable coupons: " + e.getMessage(), e);
		}
	}

	@Override
	public DiscountedCartDTO applyCouponToCart(Integer couponId, CartDTO cart) {
		Coupon coupon = couponRepository.findById(couponId)
				.orElseThrow(() -> new IllegalArgumentException("Coupon not found"));

		if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Coupon has expired");
		}

		CouponProcessor processor = CouponProcessorFactory.getProcessor(coupon.getType());
		return processor.applyCoupon(cart, coupon.getDiscount());
	}

}

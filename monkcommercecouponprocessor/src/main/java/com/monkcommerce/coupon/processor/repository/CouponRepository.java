package com.monkcommerce.coupon.processor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monkcommerce.coupon.processor.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	Optional<Coupon> findByCode(String code);

	Optional<Coupon> findById(Integer id);
}

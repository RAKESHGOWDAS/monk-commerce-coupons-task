package com.monkcommerce.coupon.processor.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CouponDTO {

	@NotNull
	@Size(min = 3, max = 50)
	private String code;

	@NotNull
	private Double discount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;

	@NotNull
	private String type;

	public CouponDTO() {
	}

	public CouponDTO(String code, Double discount, LocalDate expiryDate, String type) {
		super();
		this.code = code;
		this.discount = discount;
		this.expiryDate = expiryDate;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

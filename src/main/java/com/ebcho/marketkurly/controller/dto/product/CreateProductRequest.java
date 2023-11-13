package com.ebcho.marketkurly.controller.dto.product;

import com.ebcho.marketkurly.model.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
	@NotNull
	@Size(min = 1, max = 100)
	String name,

	@NotNull
	Category category,

	@PositiveOrZero
	long price,

	@PositiveOrZero
	long stock,

	@PositiveOrZero
	long sales,

	@NotNull
	@Size(min = 1, max = 500)
	String description
) {
}

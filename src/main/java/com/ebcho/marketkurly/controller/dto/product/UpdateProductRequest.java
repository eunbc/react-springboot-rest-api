package com.ebcho.marketkurly.controller.dto.product;

import com.ebcho.marketkurly.model.Category;

public record UpdateProductRequest(String name,
								   Category category,
								   long price,
								   long stock,
								   long sales,
								   String description) {
}

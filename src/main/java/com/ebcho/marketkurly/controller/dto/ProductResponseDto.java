package com.ebcho.marketkurly.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Product;

public record ProductResponseDto(UUID productId, String name, Category category, long price, long stock, long sales,
								 String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
	public static ProductResponseDto of(Product product) {
		return new ProductResponseDto(
			product.getProductId(),
			product.getName(),
			product.getCategory(),
			product.getPrice(),
			product.getStock(),
			product.getSales(),
			product.getDescription(),
			product.getCreatedAt(),
			product.getUpdatedAt());
	}
}

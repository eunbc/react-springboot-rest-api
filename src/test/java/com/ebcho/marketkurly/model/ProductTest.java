package com.ebcho.marketkurly.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.controller.dto.product.UpdateProductRequest;

class ProductTest {

	private Product product;
	private UUID productId;
	private LocalDateTime createdAt;

	@BeforeEach
	void setUp() {
		productId = UUID.randomUUID();
		createdAt = LocalDateTime.now();
		product = new Product(productId, "Test Product", Category.BEVERAGES, 1000, 10, 5, "Test Description", createdAt, createdAt);
	}

	@Test
	void 상품엔티티를_초기화합니다() {
		assertAll("Product initialization",
			() -> assertEquals(productId, product.getProductId()),
			() -> assertEquals("Test Product", product.getName()),
			() -> assertEquals(Category.BEVERAGES, product.getCategory()),
			() -> assertEquals(1000, product.getPrice()),
			() -> assertEquals(10, product.getStock()),
			() -> assertEquals(5, product.getSales()),
			() -> assertEquals("Test Description", product.getDescription()),
			() -> assertEquals(createdAt, product.getCreatedAt()),
			() -> assertEquals(createdAt, product.getUpdatedAt())
		);
	}

	@Test
	void 상품엔티티를_변경합니다() {
		UpdateProductRequest updateRequest = new UpdateProductRequest("Updated Product", Category.BREADS_AND_BAKERY, 1500, 15, 10, "Updated Description");
		product.changeProduct(updateRequest);

		assertAll("Product update",
			() -> assertEquals("Updated Product", product.getName()),
			() -> assertEquals(Category.BREADS_AND_BAKERY, product.getCategory()),
			() -> assertEquals(1500, product.getPrice()),
			() -> assertEquals(15, product.getStock()),
			() -> assertEquals(10, product.getSales()),
			() -> assertEquals("Updated Description", product.getDescription()),
			() -> assertTrue(product.getUpdatedAt().isAfter(createdAt))
		);
	}
}

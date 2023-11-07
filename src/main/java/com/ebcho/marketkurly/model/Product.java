package com.ebcho.marketkurly.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.controller.dto.UpdateProductRequest;

public class Product {
	private final UUID productId;
	private String name;
	private Category category;
	private long price;
	private long stock;
	private long sales;
	private String description;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Product(UUID productId, String name, Category category, long price, long stock, long sales,
		String description,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.sales = sales;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public long getPrice() {
		return price;
	}

	public long getStock() {
		return stock;
	}

	public long getSales() {
		return sales;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void change(UpdateProductRequest request) {
		this.name = request.name();
		this.category = request.category();
		this.price = request.price();
		this.stock = request.stock();
		this.sales = request.sales();
		this.description = request.description();
		this.updatedAt = LocalDateTime.now();
	}
}

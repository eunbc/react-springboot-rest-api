package com.ebcho.marketkurly.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ebcho.marketkurly.controller.dto.product.CreateProductRequest;
import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.controller.dto.product.UpdateProductRequest;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse createProduct(CreateProductRequest request) {
		Product product = new Product(
			UUID.randomUUID(),
			request.name(),
			request.category(),
			request.price(),
			request.stock(),
			request.sales(),
			request.description(),
			LocalDateTime.now(),
			LocalDateTime.now());
		Product savedProduct = productRepository.insert(product);
		return ProductResponse.of(savedProduct);
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream()
			.map(ProductResponse::of)
			.toList();
	}

	public ProductResponse getProductById(UUID id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		return ProductResponse.of(product);
	}

	public ProductResponse updateProduct(UUID id, UpdateProductRequest request) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		product.changeProduct(request);
		productRepository.update(product);
		return ProductResponse.of(product);
	}

	public void deleteProduct(UUID id) {
		productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		productRepository.deleteById(id);
	}
}

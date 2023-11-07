package com.ebcho.marketkurly.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ebcho.marketkurly.controller.dto.CreateProductRequest;
import com.ebcho.marketkurly.controller.dto.ProductResponseDto;
import com.ebcho.marketkurly.controller.dto.UpdateProductRequest;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponseDto createProduct(CreateProductRequest request) {
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
		return ProductResponseDto.of(savedProduct);
	}

	public List<ProductResponseDto> getAllProducts() {
		return productRepository.findAll().stream()
			.map(ProductResponseDto::of)
			.toList();
	}

	public ProductResponseDto getProductById(UUID id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		return ProductResponseDto.of(product);
	}

	public ProductResponseDto updateProduct(UUID id, UpdateProductRequest request) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		product.change(request);
		productRepository.update(product);
		return ProductResponseDto.of(product);
	}

	public void deleteProduct(UUID id) {
		productRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Product with ID: " + id + " not found"));
		productRepository.deleteById(id);
	}
}

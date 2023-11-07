package com.ebcho.marketkurly.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebcho.marketkurly.controller.dto.CreateProductRequest;
import com.ebcho.marketkurly.controller.dto.ProductResponseDto;
import com.ebcho.marketkurly.controller.dto.UpdateProductRequest;
import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
	private final ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequest request) {
		ProductResponseDto createdProduct = productService.createProduct(request);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); // todo: Add 'Location' header
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
		List<ProductResponseDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable UUID id) {
		ProductResponseDto product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable UUID id,
		@RequestBody UpdateProductRequest request) {
		ProductResponseDto updatedProduct = productService.updateProduct(id, request);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

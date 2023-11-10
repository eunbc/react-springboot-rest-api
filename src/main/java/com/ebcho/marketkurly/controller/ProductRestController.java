package com.ebcho.marketkurly.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebcho.marketkurly.controller.dto.product.CreateProductRequest;
import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.controller.dto.product.UpdateProductRequest;
import com.ebcho.marketkurly.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

	private final ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
		ProductResponse createdProduct = productService.createProduct(request);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{productId}")
			.buildAndExpand(createdProduct.productId())
			.toUri();
		return ResponseEntity.created(location).body(createdProduct);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
		ProductResponse product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id,
		@Valid @RequestBody UpdateProductRequest request) {
		ProductResponse updatedProduct = productService.updateProduct(id, request);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

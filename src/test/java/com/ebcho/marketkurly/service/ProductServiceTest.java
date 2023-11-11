package com.ebcho.marketkurly.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ebcho.marketkurly.controller.dto.product.CreateProductRequest;
import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.controller.dto.product.UpdateProductRequest;
import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.ProductRepository;

class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private UUID productId;
	private Product product;
	private CreateProductRequest createRequest;
	private UpdateProductRequest updateRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		productId = UUID.randomUUID();
		product = new Product(productId, "Sample Product", Category.BEVERAGES, 1000, 10, 0, "Sample Description",
			LocalDateTime.now(), LocalDateTime.now());
		createRequest = new CreateProductRequest("Sample Product", Category.BEVERAGES, 1000, 10, 0,
			"Sample Description");
		updateRequest = new UpdateProductRequest("Updated Product", Category.BEVERAGES, 1500, 15, 5,
			"Updated Description");
	}

	@Test
	void 상품_정보를_생성합니다() {
		when(productRepository.insert(any())).thenReturn(product);

		ProductResponse response = productService.createProduct(createRequest);

		verify(productRepository).insert(any());
		assertThat(response.name(), is("Sample Product"));
	}

	@Test
	void 전체_상품을_조회합니다() {
		when(productRepository.findAll()).thenReturn(List.of(product));

		List<ProductResponse> products = productService.getAllProducts();

		verify(productRepository).findAll();
		assertThat(products, hasSize(1));
		assertThat(products.get(0).name(), is("Sample Product"));
	}

	@Test
	void 상품을_상세_조회합니다() {
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		ProductResponse foundProduct = productService.getProductById(productId);

		verify(productRepository).findById(productId);
		assertThat(foundProduct.name(), is("Sample Product"));
	}

	@Test
	void 상품_정보를_변경합니다() {
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		when(productRepository.update(any())).thenReturn(product);

		ProductResponse updatedProduct = productService.updateProduct(productId, updateRequest);

		verify(productRepository).findById(productId);
		verify(productRepository).update(any());
		assertThat(updatedProduct.name(), is("Updated Product"));
	}

	@Test
	void 상품_정보를_삭제합니다() {
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		productService.deleteProduct(productId);

		verify(productRepository).findById(productId);
		verify(productRepository).deleteById(productId);
	}

}

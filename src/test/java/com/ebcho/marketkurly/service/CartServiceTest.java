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

import com.ebcho.marketkurly.controller.dto.cart.AddToCartRequest;
import com.ebcho.marketkurly.controller.dto.cart.CartItemDetailResponse;
import com.ebcho.marketkurly.controller.dto.cart.UpdateCartItemRequest;
import com.ebcho.marketkurly.model.CartItem;
import com.ebcho.marketkurly.model.CartItemDetail;
import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.CartRepository;

class CartServiceTest {

	@Mock
	private CartRepository cartRepository;

	@InjectMocks
	private CartService cartService;

	private UUID memberId;
	private AddToCartRequest addToCartRequest;
	private UpdateCartItemRequest updateCartItemRequest;
	private CartItem cartItem;
	private CartItemDetail cartItemDetail;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		memberId = UUID.randomUUID();
		long cartId = 1L;
		Product product = new Product(UUID.randomUUID(), "product", Category.BREADS_AND_BAKERY, 1000L, 100L, 10L,
			"description",
			LocalDateTime.now(), LocalDateTime.now());
		addToCartRequest = new AddToCartRequest(product.getProductId(), 3);
		updateCartItemRequest = new UpdateCartItemRequest(5);
		cartItem = CartItem.createCartItemWithoutCartId(memberId, product.getProductId(),
			addToCartRequest.quantity());
		cartItemDetail = new CartItemDetail(cartId, memberId, addToCartRequest.productId(), addToCartRequest.quantity(),
			product);
	}

	@Test
	void 장바구니에_상품을_추가합니다() {
		cartService.addProductToCart(memberId, addToCartRequest);

		verify(cartRepository).insert(any());
	}

	@Test
	void 회원의_장바구니_목록을_조회합니다() {
		when(cartRepository.findByMemberId(memberId)).thenReturn(List.of(cartItemDetail));

		List<CartItemDetailResponse> cartItems = cartService.getCartItems(memberId);

		verify(cartRepository).findByMemberId(memberId);
		assertThat(cartItems, hasSize(1));
	}

	@Test
	void 장바구니_품목을_삭제합니다() {
		long cartId = 1L;
		cartService.removeProductFromCart(cartId);

		verify(cartRepository).deleteById(cartId);
	}

	@Test
	void 장바구니_품목의_수량을_변경합니다() {
		long cartId = 1L;
		when(cartRepository.findById(cartId)).thenReturn(Optional.of(cartItem));

		cartService.updateCartItemQuantity(cartId, updateCartItemRequest);

		verify(cartRepository).findById(cartId);
		verify(cartRepository).update(any());
	}

	@Test
	void 장바구니_전체_품목을_삭제합니다() {
		cartService.clearCart(memberId);

		verify(cartRepository).clearCartByMemberId(memberId);
	}

}


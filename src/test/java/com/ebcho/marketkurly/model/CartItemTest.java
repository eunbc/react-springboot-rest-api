package com.ebcho.marketkurly.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class CartItemTest {

	private CartItem cartItem;
	private long cartId;
	private UUID memberId;
	private UUID productId;

	@BeforeEach
	void setUp() {
		cartId = 1L;
		memberId = UUID.randomUUID();
		productId = UUID.randomUUID();
		cartItem = new CartItem(cartId, memberId, productId, 5);
	}

	@Test
	void 장바구니_품목_엔티티를_초기화합니다() {
		assertAll("CartItem initialization",
			() -> assertEquals(cartId, cartItem.getCartId()),
			() -> assertEquals(memberId, cartItem.getMemberId()),
			() -> assertEquals(productId, cartItem.getProductId()),
			() -> assertEquals(5, cartItem.getQuantity())
		);
	}

	@Test
	void cartId_없이_장바구니_품목_엔티티를_초기화합니다() {
		CartItem newCartItem = CartItem.createCartItemWithoutCartId(memberId, productId, 10);

		assertAll("CartItem creation without cartId",
			() -> assertEquals(0, newCartItem.getCartId()),
			() -> assertEquals(memberId, newCartItem.getMemberId()),
			() -> assertEquals(productId, newCartItem.getProductId()),
			() -> assertEquals(10, newCartItem.getQuantity())
		);
	}

	@Test
	void 장바구니_품목의_수량을_변경합니다() {
		cartItem.changeQuantity(10);
		assertEquals(10, cartItem.getQuantity());
	}
}

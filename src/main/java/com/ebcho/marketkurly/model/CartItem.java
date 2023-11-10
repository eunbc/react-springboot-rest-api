package com.ebcho.marketkurly.model;

import java.util.UUID;

public class CartItem {
	private final long cartId;
	private final UUID memberId;
	private final UUID productId;
	private int quantity;

	public CartItem(long cartId, UUID memberId, UUID productId, int quantity) {
		this.cartId = cartId;
		this.memberId = memberId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public static CartItem createCartItemWithoutCartId(UUID memberId, UUID productId, int quantity) {
		return new CartItem(0, memberId, productId, quantity);
	}

	public void changeQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCartId() {
		return cartId;
	}

	public UUID getMemberId() {
		return memberId;
	}

	public UUID getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

}

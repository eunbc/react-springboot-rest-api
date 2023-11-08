package com.ebcho.marketkurly.model;

import java.util.UUID;

public class CartItemDetail extends CartItem{

	private final Product product;

	public CartItemDetail(long cartId, UUID memberId, UUID productId, int quantity, Product product) {
		super(cartId, memberId, productId, quantity);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
}

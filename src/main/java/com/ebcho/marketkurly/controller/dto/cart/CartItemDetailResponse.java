package com.ebcho.marketkurly.controller.dto.cart;

import java.util.UUID;

import com.ebcho.marketkurly.model.CartItemDetail;
import com.ebcho.marketkurly.model.Product;

public record CartItemDetailResponse(long cartId, UUID productId, String name, long price, int quantity) {
	public static CartItemDetailResponse of(CartItemDetail cartItemDetail) {
		Product product = cartItemDetail.getProduct();
		return new CartItemDetailResponse(
			cartItemDetail.getCartId(),
			cartItemDetail.getProductId(),
			product.getName(),
			product.getPrice(),
			cartItemDetail.getQuantity());
	}
}

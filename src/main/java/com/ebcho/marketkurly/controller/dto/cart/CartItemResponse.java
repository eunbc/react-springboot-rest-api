package com.ebcho.marketkurly.controller.dto.cart;

import java.util.UUID;

import com.ebcho.marketkurly.model.CartItem;

public record CartItemResponse(long cardId, UUID memberId, UUID productId, int quantity) {

	public static CartItemResponse of(CartItem cartItem) {
		return new CartItemResponse(cartItem.getCartId(), cartItem.getMemberId(), cartItem.getProductId(),
			cartItem.getQuantity());
	}
}

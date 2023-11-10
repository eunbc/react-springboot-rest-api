package com.ebcho.marketkurly.controller.dto.order;

import java.util.UUID;

import com.ebcho.marketkurly.model.OrderItem;

public record OrderItemResponse(UUID productId, String name, long price, int quantity) {

	public static OrderItemResponse of(OrderItem orderItem) {
		return new OrderItemResponse(orderItem.product().getProductId(), orderItem.product().getName(),
			orderItem.price(), orderItem.quantity());
	}
}

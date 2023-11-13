package com.ebcho.marketkurly.controller.dto.order;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.model.Order;
import com.ebcho.marketkurly.model.OrderStatus;

public record OrderResponse(UUID orderId,
							OrderStatus orderStatus,
							int totalItems,
							long totalPrice,
							LocalDateTime createdAt) {
	public static OrderResponse of(Order order) {
		int totalItems = order.getOrderItems().size();
		long totalPrice = order.getOrderItems().stream()
			.mapToLong(item -> item.price() * item.quantity())
			.sum();

		return new OrderResponse(
			order.getOrderId(),
			order.getOrderStatus(),
			totalItems,
			totalPrice,
			order.getCreatedAt()
		);
	}
}

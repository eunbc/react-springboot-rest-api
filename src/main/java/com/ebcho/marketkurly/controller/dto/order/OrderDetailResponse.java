package com.ebcho.marketkurly.controller.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.model.Order;
import com.ebcho.marketkurly.model.OrderStatus;

public record OrderDetailResponse(UUID memberId,
								  String name,
								  String address,
								  UUID orderId,
								  OrderStatus orderStatus,
								  LocalDateTime createdAt,
								  LocalDateTime updatedAt,
								  List<OrderItemResponse> orderItems) {
	public static OrderDetailResponse from(Order order, Member member) {
		List<OrderItemResponse> orderItemResponseList = order.getOrderItems()
			.stream()
			.map(OrderItemResponse::of)
			.toList();
		return new OrderDetailResponse(member.getMemberId(), member.getName(), member.getAddress(), order.getOrderId(),
			order.getOrderStatus(), order.getCreatedAt(), order.getUpdatedAt(), orderItemResponseList);
	}
}


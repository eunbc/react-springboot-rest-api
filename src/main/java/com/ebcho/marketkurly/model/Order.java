package com.ebcho.marketkurly.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
	private final UUID orderId;
	private final UUID memberId;
	private OrderStatus orderStatus;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<OrderItem> orderItems = new ArrayList<>(); // todo : 이렇게 해도 되나?

	public Order(UUID orderId, UUID memberId, List<OrderItem> orderItems) {
		this.orderId = orderId;
		this.memberId = memberId;
		this.orderStatus = OrderStatus.ORDERED;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.orderItems = orderItems;
	}

	public Order(UUID orderId, UUID memberId, OrderStatus orderStatus, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.orderId = orderId;
		this.memberId = memberId;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public UUID getMemberId() {
		return memberId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setOrderItems(List<OrderItem> items) {
		this.orderItems = items;
	}
}

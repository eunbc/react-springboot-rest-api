package com.ebcho.marketkurly.controller.dto.order;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
	@NotNull
	UUID memberId,
	@NotEmpty
	List<CreateOrderItemRequest> orderItems
) {
}


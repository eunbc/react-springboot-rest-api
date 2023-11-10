package com.ebcho.marketkurly.controller.dto.order;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(UUID memberId, List<CreateOrderItemRequest> orderItems) {
}

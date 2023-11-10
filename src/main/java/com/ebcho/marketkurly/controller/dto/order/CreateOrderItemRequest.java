package com.ebcho.marketkurly.controller.dto.order;

import java.util.UUID;

public record CreateOrderItemRequest(UUID productId, long price, int quantity) {
}

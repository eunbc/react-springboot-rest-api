package com.ebcho.marketkurly.controller.dto.order;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateOrderItemRequest(@NotNull UUID productId, @PositiveOrZero long price, @Positive int quantity) {
}

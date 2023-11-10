package com.ebcho.marketkurly.controller.dto.cart;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddToCartRequest(@NotNull UUID productId, @Positive int quantity) {
}

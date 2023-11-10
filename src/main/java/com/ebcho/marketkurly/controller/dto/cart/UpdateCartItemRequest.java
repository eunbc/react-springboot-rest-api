package com.ebcho.marketkurly.controller.dto.cart;

import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequest(@Positive int quantity) {
}

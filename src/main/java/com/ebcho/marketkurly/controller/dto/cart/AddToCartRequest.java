package com.ebcho.marketkurly.controller.dto.cart;

import java.util.UUID;

public record AddToCartRequest(UUID productId, int quantity) {
}

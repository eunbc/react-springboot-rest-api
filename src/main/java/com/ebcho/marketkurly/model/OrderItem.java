package com.ebcho.marketkurly.model;

import java.util.UUID;

public record OrderItem(UUID productId, long price, int quantity) {
}

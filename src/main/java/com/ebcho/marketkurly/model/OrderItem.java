package com.ebcho.marketkurly.model;

public record OrderItem(Product product, long price, int quantity) {
}

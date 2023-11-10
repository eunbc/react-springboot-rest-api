package com.ebcho.marketkurly.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ebcho.marketkurly.model.Order;

public interface OrderRepository {

	Order insert(Order order);

	List<Order> findAll();

	Optional<Order> findById(UUID orderId);
}

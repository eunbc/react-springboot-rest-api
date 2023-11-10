package com.ebcho.marketkurly.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebcho.marketkurly.controller.dto.order.CreateOrderRequest;
import com.ebcho.marketkurly.controller.dto.order.OrderDetailResponse;
import com.ebcho.marketkurly.controller.dto.order.OrderResponse;
import com.ebcho.marketkurly.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

	private final OrderService orderService;

	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest orderRequest) {
		OrderResponse orderResponse = orderService.createOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		List<OrderResponse> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailResponse> getOrderById(@PathVariable UUID id) {
		OrderDetailResponse order = orderService.getOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}

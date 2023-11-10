package com.ebcho.marketkurly.view;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebcho.marketkurly.controller.dto.order.OrderDetailResponse;
import com.ebcho.marketkurly.controller.dto.order.OrderResponse;
import com.ebcho.marketkurly.service.OrderService;

@Controller
public class AdminOrderController {

	private OrderService orderService;

	public AdminOrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/orders")
	public String getAllOrders(Model model) {
		List<OrderResponse> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	@GetMapping("/orders/{orderId}")
	public String getOrderById(@PathVariable UUID orderId, Model model) {
		OrderDetailResponse order = orderService.getOrderById(orderId);
		model.addAttribute("order",order);
		return "order/detail";
	}
}

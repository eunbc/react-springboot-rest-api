package com.ebcho.marketkurly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebcho.marketkurly.controller.dto.order.CreateOrderItemRequest;
import com.ebcho.marketkurly.controller.dto.order.CreateOrderRequest;
import com.ebcho.marketkurly.controller.dto.order.OrderDetailResponse;
import com.ebcho.marketkurly.controller.dto.order.OrderResponse;
import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.model.Order;
import com.ebcho.marketkurly.model.OrderItem;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.MemberRepository;
import com.ebcho.marketkurly.repository.OrderRepository;
import com.ebcho.marketkurly.repository.ProductRepository;

@Service
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final CartService cartService;

	public OrderService(OrderRepository orderRepository, MemberRepository memberRepository,
		ProductRepository productRepository, CartService cartService) {
		this.orderRepository = orderRepository;
		this.memberRepository = memberRepository;
		this.productRepository = productRepository;
		this.cartService = cartService;
	}

	public OrderResponse createOrder(CreateOrderRequest request) {
		List<CreateOrderItemRequest> orderItemRequests = request.orderItems();

		List<OrderItem> orderItems = new ArrayList<>();
		for (CreateOrderItemRequest orderItemRequest : orderItemRequests) {
			Product product = productRepository.findById(orderItemRequest.productId())
				.orElseThrow(
					() -> new NoSuchElementException("Product not found for ID: " + orderItemRequest.productId()));
			OrderItem orderItem = new OrderItem(product, orderItemRequest.price(), orderItemRequest.quantity());
			orderItems.add(orderItem);
		}

		Order order = new Order(
			UUID.randomUUID(),
			request.memberId(),
			orderItems
		);

		// 주문 저장
		Order insertedOrder = orderRepository.insert(order);

		// 장바구니 비우기
		cartService.clearCart(request.memberId());
		return OrderResponse.of(insertedOrder);
	}

	public List<OrderResponse> getAllOrders() {
		return orderRepository.findAll().stream()
			.map(OrderResponse::of)
			.toList();
	}

	public OrderDetailResponse getOrderById(UUID orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new NoSuchElementException("Order with ID: " + orderId + " not found"));
		Member member = memberRepository.findById(order.getMemberId())
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + order.getMemberId() + " not found"));

		return OrderDetailResponse.from(order, member);
	}
}

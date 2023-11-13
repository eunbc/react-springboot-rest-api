package com.ebcho.marketkurly.service;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ebcho.marketkurly.controller.dto.order.CreateOrderItemRequest;
import com.ebcho.marketkurly.controller.dto.order.CreateOrderRequest;
import com.ebcho.marketkurly.controller.dto.order.OrderDetailResponse;
import com.ebcho.marketkurly.controller.dto.order.OrderResponse;
import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.model.Order;
import com.ebcho.marketkurly.model.OrderItem;
import com.ebcho.marketkurly.model.Product;
import com.ebcho.marketkurly.repository.MemberRepository;
import com.ebcho.marketkurly.repository.OrderRepository;
import com.ebcho.marketkurly.repository.ProductRepository;

class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CartService cartService;

	@InjectMocks
	private OrderService orderService;

	private UUID orderId;
	private Order order;
	private CreateOrderRequest createOrderRequest;
	private Member member;
	private Product product;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		orderId = UUID.randomUUID();
		UUID memberId = UUID.randomUUID();
		UUID productId = UUID.randomUUID();

		product = new Product(productId, "Sample Product", Category.BEVERAGES, 1000, 10, 0, "Sample Description", LocalDateTime.now(), LocalDateTime.now());
		CreateOrderItemRequest orderItemRequest = new CreateOrderItemRequest(productId, 1000, 5);
		createOrderRequest = new CreateOrderRequest(memberId, List.of(orderItemRequest));

		order = new Order(orderId, memberId, List.of(new OrderItem(product, 1000, 5)));
		member = new Member(memberId, "홍길동", "hong@example.com", "서울특별시 어딘가", LocalDateTime.now(), LocalDateTime.now());
	}

	@Test
	void 주문을_생성합니다() {
		when(productRepository.findById(any())).thenReturn(Optional.of(product));
		when(orderRepository.insert(any())).thenReturn(order);

		OrderResponse response = orderService.createOrder(createOrderRequest);

		verify(productRepository).findById(any());
		verify(orderRepository).insert(any());
		verify(cartService).clearCart(any());
		assertThat(response.orderId(), is(orderId));
	}

	@Test
	void 주문_목록을_전체_조회합니다() {
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		when(orderRepository.findAll()).thenReturn(orderList);

		List<OrderResponse> responses = orderService.getAllOrders();

		verify(orderRepository).findAll();
		assertThat(responses, hasSize(1));
		assertThat(responses.get(0).orderId(), is(orderId));
	}

	@Test
	void 주문_상세_정보를_조회합니다() {
		when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
		when(memberRepository.findById(order.getMemberId())).thenReturn(Optional.of(member));

		OrderDetailResponse detailResponse = orderService.getOrderById(orderId);

		verify(orderRepository).findById(orderId);
		verify(memberRepository).findById(order.getMemberId());
		assertThat(detailResponse.orderId(), is(orderId));
	}

}


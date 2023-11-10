package com.ebcho.marketkurly.repository;

import static com.ebcho.marketkurly.util.JdbcUtils.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Order;
import com.ebcho.marketkurly.model.OrderItem;
import com.ebcho.marketkurly.model.OrderStatus;
import com.ebcho.marketkurly.model.Product;

@Repository
public class OrderJdbcRepository implements OrderRepository {

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
		UUID orderId = toUUID(rs.getBytes("order_id"));
		UUID memberId = toUUID(rs.getBytes("member_id"));
		OrderStatus orderStatus = OrderStatus.valueOf(rs.getString("order_status"));
		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		LocalDateTime updatedAt =
			rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null;

		return new Order(orderId, memberId, orderStatus, createdAt, updatedAt);
	};

	private final RowMapper<OrderItem> orderItemRowMapper = (rs, rowNum) -> {
		UUID productId = toUUID(rs.getBytes("product_id"));
		String name = rs.getString("name");
		Category category = Category.valueOf(rs.getString("category"));
		long productPrice = rs.getLong("product_price");
		long stock = rs.getLong("stock");
		long sales = rs.getLong("sales");
		String description = rs.getString("description");
		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		LocalDateTime updatedAt =
			rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null;

		Product product = new Product(
			productId, name, category, productPrice, stock, sales,
			description, createdAt, updatedAt
		);

		long orderItemPrice = rs.getLong("order_item_price");
		int quantity = rs.getInt("quantity");

		return new OrderItem(product, orderItemPrice, quantity);
	};

	public OrderJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Order insert(Order order) {
		String insertOrderSql = "INSERT INTO `order` (order_id, member_id, order_status, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertOrderSql,
			toBytes(order.getOrderId()),
			toBytes(order.getMemberId()),
			order.getOrderStatus().name(),
			order.getCreatedAt(),
			order.getUpdatedAt());

		String insertOrderItemSql = "INSERT INTO order_item (order_id, product_id, price, quantity) VALUES (?, ?, ?, ?)";
		order.getOrderItems().forEach(orderItem -> jdbcTemplate.update(
			insertOrderItemSql,
			toBytes(order.getOrderId()),
			toBytes(orderItem.product().getProductId()),
			orderItem.product().getPrice(),
			orderItem.quantity()
		));

		return order;
	}

	@Override
	public List<Order> findAll() {
		final String sql = "SELECT * FROM `order`";
		List<Order> orders = jdbcTemplate.query(sql, orderRowMapper);

		orders.forEach(order -> {
			String itemSql = "SELECT oi.order_id, oi.product_id, oi.price AS order_item_price, oi.quantity, "
				+ "p.product_id, p.name, p.category, p.price AS product_price, p.stock, p.sales, p.description, p.created_at, p.updated_at "
				+ "FROM order_item oi JOIN product p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
			List<OrderItem> items = jdbcTemplate.query(itemSql, orderItemRowMapper, toBytes(order.getOrderId()));
			order.setOrderItems(items);
		});
		return orders;
	}

	@Override
	public Optional<Order> findById(UUID orderId) {
		final String orderSql = "SELECT * FROM `order` WHERE order_id = ?";
		List<Order> orders = jdbcTemplate.query(orderSql, orderRowMapper, toBytes(orderId));

		// 주문이 존재하지 않으면 빈 Optional 반환
		if (orders.isEmpty()) {
			return Optional.empty();
		}

		Order order = orders.get(0);

		String itemSql = "SELECT oi.order_id, oi.product_id, oi.price AS order_item_price, oi.quantity, "
			+ "p.product_id, p.name, p.category, p.price AS product_price, p.stock, p.sales, p.description, p.created_at, p.updated_at "
			+ "FROM order_item oi JOIN product p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
		List<OrderItem> items = jdbcTemplate.query(itemSql, orderItemRowMapper, toBytes(orderId));
		order.setOrderItems(items);

		return Optional.of(order);
	}
}

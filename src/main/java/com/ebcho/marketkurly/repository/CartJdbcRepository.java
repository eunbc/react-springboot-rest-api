package com.ebcho.marketkurly.repository;

import static com.ebcho.marketkurly.util.JdbcUtils.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ebcho.marketkurly.model.CartItem;
import com.ebcho.marketkurly.model.CartItemDetail;
import com.ebcho.marketkurly.model.Category;
import com.ebcho.marketkurly.model.Product;

@Repository
public class CartJdbcRepository implements CartRepository {

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<CartItem> cartItemRowMapper = (rs, rowNum) -> new CartItem(
		rs.getLong("cart_id"),
		toUUID(rs.getBytes("member_id")),
		toUUID(rs.getBytes("product_id")),
		rs.getInt("quantity")
	);

	private final RowMapper<CartItemDetail> cartItemWithProductDetailsRowMapper = (rs, rowNum) -> {
		long cartId = rs.getLong("cart_id");
		UUID memberId = toUUID(rs.getBytes("member_id"));
		UUID productId = toUUID(rs.getBytes("product_id"));
		int quantity = rs.getInt("quantity");

		Product product = new Product(
			productId,
			rs.getString("name"),
			Category.valueOf(rs.getString("category")),
			rs.getLong("price"),
			rs.getLong("stock"),
			rs.getLong("sales"),
			rs.getString("description"),
			rs.getTimestamp("created_at").toLocalDateTime(),
			rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
		);

		return new CartItemDetail(cartId, memberId, productId, quantity, product);
	};

	public CartJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(CartItem cartItem) {
		final String sql = "INSERT INTO cart_item (member_id, product_id, quantity) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, toBytes(cartItem.getMemberId()), toBytes(cartItem.getProductId()),
			cartItem.getQuantity());
	}

	@Override
	public void update(CartItem cartItem) {
		final String sql = "UPDATE cart_item SET quantity = ? WHERE cart_id = ?";
		jdbcTemplate.update(sql, cartItem.getQuantity(), cartItem.getCartId());
	}

	@Override
	public List<CartItemDetail> findByMemberId(UUID memberId) {
		final String sql = "SELECT ci.cart_id, ci.member_id, ci.product_id, ci.quantity, " +
			"p.product_id, p.name, p.category, p.price, p.stock, p.sales, p.description, p.created_at, p.updated_at " +
			"FROM cart_item ci " +
			"JOIN product p ON ci.product_id = p.product_id " +
			"WHERE ci.member_id = ?";

		return jdbcTemplate.query(sql, cartItemWithProductDetailsRowMapper, toBytes(memberId));
	}

	@Override
	public Optional<CartItem> findById(long cartId) {
		final String sql = "SELECT * FROM cart_item WHERE cart_id = ? ";
		List<CartItem> cartItems = jdbcTemplate.query(sql, cartItemRowMapper, cartId);
		return cartItems.stream().findFirst();
	}

	@Override
	public void deleteById(long cartId) {
		final String sql = "DELETE FROM cart_item WHERE cart_id = ?";
		jdbcTemplate.update(sql, cartId);
	}

	@Override
	public void clearCartByMemberId(UUID memberId) {
		final String sql = "DELETE FROM cart_item WHERE member_id = ?";
		jdbcTemplate.update(sql, toBytes(memberId));
	}

}

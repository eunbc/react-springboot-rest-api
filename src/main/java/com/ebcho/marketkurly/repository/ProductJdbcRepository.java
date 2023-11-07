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
import com.ebcho.marketkurly.model.Product;

@Repository
public class ProductJdbcRepository implements ProductRepository {
	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
		UUID productId = toUUID(rs.getBytes("product_id"));
		String name = rs.getString("name");
		Category category = Category.valueOf(rs.getString("category"));
		long price = rs.getLong("price");
		long stock = rs.getLong("stock");
		long sales = rs.getLong("sales");
		String description = rs.getString("description");
		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		LocalDateTime updatedAt = rs.getTimestamp("updated_at") != null
			? rs.getTimestamp("updated_at").toLocalDateTime()
			: null;
		return new Product(productId, name, category, price, stock, sales, description, createdAt, updatedAt);
	};

	public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Product insert(Product product) {
		final String sql = "INSERT INTO product (product_id, name, category, price, stock, sales, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,
			toBytes(product.getProductId()),
			product.getName(),
			product.getCategory().toString(),
			product.getPrice(),
			product.getStock(),
			product.getSales(),
			product.getDescription(),
			product.getCreatedAt(),
			product.getUpdatedAt());
		return product;
	}

	@Override
	public Product update(Product product) {
		final String sql = "UPDATE product SET name = ?, category = ?, price = ?, stock = ?, sales = ?, description = ?, updated_at = ? WHERE product_id = ?";
		jdbcTemplate.update(sql,
			product.getName(),
			product.getCategory().toString(),
			product.getPrice(),
			product.getStock(),
			product.getSales(),
			product.getDescription(),
			product.getUpdatedAt(),
			toBytes(product.getProductId()));
		return product;
	}

	@Override
	public List<Product> findAll() {
		final String sql = "SELECT * FROM product";
		return jdbcTemplate.query(sql, productRowMapper);
	}

	@Override
	public Optional<Product> findById(UUID productId) {
		final String sql = "SELECT * FROM product WHERE product_id = ?";
		List<Product> products = jdbcTemplate.query(sql, productRowMapper, toBytes(productId));
		return products.stream().findFirst();
	}

	@Override
	public void deleteById(UUID productId) {
		final String sql = "DELETE FROM product WHERE product_id = ?";
		jdbcTemplate.update(sql, toBytes(productId));
	}

}

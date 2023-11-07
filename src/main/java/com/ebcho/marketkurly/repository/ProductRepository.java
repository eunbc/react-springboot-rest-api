package com.ebcho.marketkurly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ebcho.marketkurly.model.Product;

public interface ProductRepository {

	Product insert(Product product);

	Product update(Product product);

	List<Product> findAll();

	Optional<Product> findById(UUID productId);

	void deleteById(UUID productId);
}

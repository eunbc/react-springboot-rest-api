package com.ebcho.marketkurly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ebcho.marketkurly.model.CartItem;
import com.ebcho.marketkurly.model.CartItemDetail;

public interface CartRepository {

	void insert(CartItem cartItem);

	void update(CartItem cartItem);

	List<CartItemDetail> findByMemberId(UUID memberId);

	Optional<CartItem> findByMemberIdAndProductId(UUID memberId, UUID productId);

	void deleteByMemberIdAndProductId(UUID memberId, UUID productId);
}

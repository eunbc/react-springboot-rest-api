package com.ebcho.marketkurly.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebcho.marketkurly.controller.dto.cart.AddToCartRequest;
import com.ebcho.marketkurly.controller.dto.cart.CartItemDetailResponse;
import com.ebcho.marketkurly.controller.dto.cart.UpdateCartItemRequest;
import com.ebcho.marketkurly.model.CartItem;
import com.ebcho.marketkurly.model.CartItemDetail;
import com.ebcho.marketkurly.repository.CartRepository;
import com.ebcho.marketkurly.repository.MemberRepository;
import com.ebcho.marketkurly.repository.ProductRepository;

@Service
@Transactional
public class CartService {

	private final CartRepository cartRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;

	public CartService(CartRepository cartRepository, MemberRepository memberRepository,
		ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.memberRepository = memberRepository;
		this.productRepository = productRepository;
	}

	public void addProductToCart(UUID memberId, AddToCartRequest request) {
		cartRepository.insert(CartItem.createCartItemWithoutCartId(memberId, request.productId(), request.quantity()));
	}

	public List<CartItemDetailResponse> getCartItems(UUID memberId) {
		List<CartItemDetail> cartItemDetails = cartRepository.findByMemberId(memberId);
		return cartItemDetails.stream()
			.map(CartItemDetailResponse::of)
			.toList();
	}

	public void removeProductFromCart(long cartId) {
		cartRepository.deleteById(cartId);
	}

	public void updateCartItemQuantity(long cartId, UpdateCartItemRequest request) {
		CartItem cartItem = cartRepository.findById(cartId).orElseThrow(
			() -> new NoSuchElementException("No cart found with ID: " + cartId));
		cartItem.changeQuantity(request.quantity());
		cartRepository.update(cartItem);
	}

	public void clearCart(UUID memberId) {
		cartRepository.clearCartByMemberId(memberId);
	}

	private void validateMemberAndProduct(UUID memberId, UUID productId) {
		memberRepository.findById(memberId)
			.orElseThrow(() -> new NoSuchElementException("No member found with ID: " + memberId));

		productRepository.findById(productId)
			.orElseThrow(() -> new NoSuchElementException("No product found with ID: " + productId));
	}

}

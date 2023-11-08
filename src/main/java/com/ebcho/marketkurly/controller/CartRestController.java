package com.ebcho.marketkurly.controller;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebcho.marketkurly.controller.dto.cart.AddToCartRequest;
import com.ebcho.marketkurly.controller.dto.cart.CartItemDetailResponse;
import com.ebcho.marketkurly.controller.dto.cart.CartItemResponse;
import com.ebcho.marketkurly.controller.dto.cart.UpdateCartItemRequest;
import com.ebcho.marketkurly.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestController {

	private final CartService cartService;

	public CartRestController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/{memberId}")
	public ResponseEntity<Void> addProductToCart(@PathVariable UUID memberId,
		@RequestBody AddToCartRequest request) {
		cartService.addProductToCart(memberId, request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<List<CartItemDetailResponse>> getCartItems(@PathVariable UUID memberId) {
		List<CartItemDetailResponse> cartItems = cartService.getCartItems(memberId);
		return new ResponseEntity<>(cartItems, HttpStatus.OK);
	}

	@DeleteMapping("/{memberId}/{productId}")
	public ResponseEntity<Void> removeProductFromCart(@PathVariable UUID memberId,
		@PathVariable UUID productId) {
		cartService.removeProductFromCart(memberId, productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{memberId}/{productId}")
	public ResponseEntity<Void> updateCartItemQuantity(@PathVariable UUID memberId,
		@PathVariable UUID productId,
		@RequestBody UpdateCartItemRequest request) {
		cartService.updateCartItemQuantity(memberId, productId, request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

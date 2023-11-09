package com.ebcho.marketkurly.view;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebcho.marketkurly.controller.dto.cart.CartItemDetailResponse;
import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.service.CartService;
import com.ebcho.marketkurly.service.MemberService;

@Controller
public class CartController {

	private final CartService cartService;
	private final MemberService memberService;

	public CartController(CartService cartService, MemberService memberService) {
		this.cartService = cartService;
		this.memberService = memberService;
	}

	@GetMapping("/carts")
	public String getCartHome(Model model) {
		List<MemberResponse> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "cart/index";
	}

	@GetMapping("/carts/{memberId}")
	public String getCartsAll(@PathVariable UUID memberId, Model model) {
		List<MemberResponse> members = memberService.getAllMembers();
		List<CartItemDetailResponse> cartItemDetailResponseList = cartService.getCartItems(memberId);
		model.addAttribute("carts", cartItemDetailResponseList);
		model.addAttribute("members", members);
		model.addAttribute("memberId", memberId);
		return "cart/list";
	}

}

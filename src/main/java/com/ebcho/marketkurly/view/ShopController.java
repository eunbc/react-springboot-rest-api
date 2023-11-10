package com.ebcho.marketkurly.view;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.service.MemberService;
import com.ebcho.marketkurly.service.ProductService;

@Controller
public class ShopController {

	private final ProductService productService;
	private final MemberService memberService;

	public ShopController(ProductService productService, MemberService memberService) {
		this.productService = productService;
		this.memberService = memberService;
	}

	@GetMapping("/shop/{productId}")
	public String getProductDetail(@PathVariable UUID productId, Model model) {
		ProductResponse product = productService.getProductById(productId);
		List<MemberResponse> members = memberService.getAllMembers();
		model.addAttribute("product", product);
		model.addAttribute("members", members);
		return "shop/detail";
	}

}

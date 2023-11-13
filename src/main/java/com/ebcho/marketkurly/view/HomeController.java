package com.ebcho.marketkurly.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.service.ProductService;

@Controller
public class HomeController {

	private final ProductService productService;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String index(Model model) {
		List<ProductResponse> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "shop/list";
	}

}

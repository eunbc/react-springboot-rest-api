package com.ebcho.marketkurly.view;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebcho.marketkurly.controller.dto.product.ProductResponse;
import com.ebcho.marketkurly.service.ProductService;

@Controller
public class AdminProductController {

	private final ProductService productService;

	public AdminProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public String getProductsAll(Model model) {
		List<ProductResponse> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "product/list";
	}

	@GetMapping("/products/{productId}")
	public String getProductDetail(@PathVariable UUID productId, Model model) {
		ProductResponse product = productService.getProductById(productId);
		model.addAttribute("product", product);
		return "product/detail";
	}

	@GetMapping("/products/create")
	public String getProductCreateForm() {
		return "product/create";
	}

	@GetMapping("/products/update/{productId}")
	public String getProductUpdateForm(@PathVariable UUID productId, Model model) {
		ProductResponse product = productService.getProductById(productId);
		model.addAttribute("product", product);
		return "product/update";
	}
}

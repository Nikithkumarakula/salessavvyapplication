package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/products")
public class ProductController {
	
	ProductService productService;
	
	
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	

	@GetMapping()
	public ResponseEntity<Map<String,Object>> getProducts(@RequestParam String category, HttpServletRequest request) {
		
		try {
		User authenticatedUser = (User) request.getAttribute("authenticatedUser");
		
		if(authenticatedUser==null) {
			return ResponseEntity.status(401).body(Map.of("Error","Unauthorized Accessed"));
		}
		
		
		//deta get it from Service
		List<Product> products = productService.getProductsByCategory(category);
		
		// Create response map
		Map<String, Object> response = new HashMap<>();
		
		
		//Create user map
		Map<String,String> userInfo = new HashMap<>();
		
		userInfo.put("name", authenticatedUser.getUsername());
		userInfo.put("role", authenticatedUser.getRole().name());
		
		//Adding usermap to response
		response.put("user", userInfo);
		
		List<Map<String,Object>> productList = new ArrayList<>();
		
		for (Product product : products) {
			Map<String,Object> productDetails = new HashMap<>();
			productDetails.put("product_id", product.getProductId());
			productDetails.put("name", product.getName());
			productDetails.put("description", product.getDescription());
			productDetails.put("price", product.getPrice());
			productDetails.put("stock", product.getStock());
			
			
			List<String> images = productService.getProductImage(product.getProductId());
			productDetails.put("images", images);
			
			productList.add(productDetails);
			
		}
		
		response.put("products", productList);
		
		return ResponseEntity.ok(response);
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
			
		}
	}





	
}

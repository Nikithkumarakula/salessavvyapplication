package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public int getCartItemCount(int userId) {
        return cartRepository.countTotalItems(userId);
    }

    public void addToCart(int userId, int productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Optional<CartItem> existingItem = cartRepository.findByUserAndProduct(userId, productId);
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepository.save(cartItem);
        } else {
            CartItem newItem = new CartItem(user, product, quantity);
            cartRepository.save(newItem);
        }
    }

    public Map<String, Object> getCartItems(int userId) {
        List<CartItem> cartItems = cartRepository.findCartItemsWithProductDetails(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("role", user.getRole().name());

        List<Map<String, Object>> products = new ArrayList<>();
        double overallTotalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            List<ProductImage> productImages = productImageRepository.findByProduct_ProductId(product.getProductId());
            String imageUrl = productImages.isEmpty() ? "default-image-url" : productImages.get(0).getImageUrl();

            Map<String, Object> productDetails = new HashMap<>();
            productDetails.put("product_id", product.getProductId());
            productDetails.put("image_url", imageUrl);
            productDetails.put("name", product.getName());
            productDetails.put("description", product.getDescription());
            productDetails.put("price_per_unit", product.getPrice());
            productDetails.put("quantity", cartItem.getQuantity());
            productDetails.put("total_price", cartItem.getQuantity() * product.getPrice().doubleValue());

            products.add(productDetails);
            overallTotalPrice += cartItem.getQuantity() * product.getPrice().doubleValue();
        }

        Map<String, Object> cart = new HashMap<>();
        cart.put("products", products);
        cart.put("overall_total_price", overallTotalPrice);
        response.put("cart", cart);

        return response;
    }

    public void updateCartItemQuantity(int userId, int productId, int quantity) {
        Optional<CartItem> existingItem = cartRepository.findByUserAndProduct(userId, productId);
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            if (quantity <= 0) {
                deleteCartItem(userId, productId);
            } else {
                cartItem.setQuantity(quantity);
                cartRepository.save(cartItem);
            }
        }
    }

    public void deleteCartItem(int userId, int productId) {
        cartRepository.deleteCartItem(userId, productId);
    }

    public void deleteAllCartItems(int userId) {
        cartRepository.deleteAllCartItem(userId);
    }
}
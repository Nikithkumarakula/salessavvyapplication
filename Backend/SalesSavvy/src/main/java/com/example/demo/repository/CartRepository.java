package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CartItem;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    
    @Query("SELECT c FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId") 
    Optional<CartItem> findByUserAndProduct(@Param("userId") int userId, @Param("productId") int productId);

    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM CartItem c WHERE c.user.userId = :userId") 
    int countTotalItems(@Param("userId") int userId);
    
    @Query("SELECT c FROM  CartItem c JOIN Fetch c.product p LEFT JOIN ProductImage pi ON p.productId = pi.product.productId WHERE c.user.userId = :userId ")
    List<CartItem> findCartItemsWithProductDetails(int userId);
    
    
    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity Where c.id = :cartItemId")
    public void updateCartItemQuantity(int cartItemId,int quantity);
    
//    @Modifying
//    @Transactional
//    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
//    void updateCartItemQuantity(@Param("cartItemId") int cartItemId, @Param("quantity") int quantity);

    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId")
    public void deleteCartItem(int userId,int productId);
    
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId")
//    void deleteCartItem(@Param("userId") int userId, @Param("productId") int productId);

    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId")
    void deleteAllCartItem(int userId);

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findAllByCategory_CategoryId(Integer categoryId);
	

    @Query("SELECT p.category.categoryName from Product p where p.productId = :productId")
    String findCategoryNameByProduct_productId(int productId);
}

package com.PrunnyProductCatalog.EcommerceProductCatalog.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(long categoryId);
	
	@Query("SELECT p FROM Product p WHERE "
	         + "(:categoryId IS NULL OR p.category.id = :categoryId) AND "
	         + "(:price IS NULL OR p.price = :price)")
	    List<Product> findByCategoryAndPrice(
	        @Param("categoryId") Long categoryId,
	        @Param("price") Double price
	    );
}

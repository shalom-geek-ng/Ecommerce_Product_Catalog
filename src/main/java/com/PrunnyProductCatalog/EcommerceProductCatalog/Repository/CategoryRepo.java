package com.PrunnyProductCatalog.EcommerceProductCatalog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}

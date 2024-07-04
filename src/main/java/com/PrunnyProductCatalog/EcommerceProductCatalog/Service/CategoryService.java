package com.PrunnyProductCatalog.EcommerceProductCatalog.Service;



import java.util.List;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	List<CategoryDto> getCategories();
	CategoryDto updateCategory(long categoryId, CategoryDto categoryDto);
	CategoryDto getCategoryById(long categoryId);
	void deleteCategoryById(long categoryId);
}

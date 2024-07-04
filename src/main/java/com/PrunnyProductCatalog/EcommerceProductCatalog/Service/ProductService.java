package com.PrunnyProductCatalog.EcommerceProductCatalog.Service;

import java.util.List;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.ProductDto;

public interface ProductService {
	ProductDto createProducts(ProductDto product, long categoryId);
	List<ProductDto> getAllProducts(long categoryId);
	ProductDto getProductById(long categoryId, long productId);
	ProductDto updateProductById(long categoryId, long productId,ProductDto product);
	void deleteProductById(long categoryId, long productId);
	 List<ProductDto> searchProducts(Long categoryId, Double price);
}

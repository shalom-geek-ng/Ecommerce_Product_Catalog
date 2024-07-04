package com.PrunnyProductCatalog.EcommerceProductCatalog.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Category;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Product;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Exceptions.ResponseNotFoundException;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.CategoryDto;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.ProductDto;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Repository.CategoryRepo;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	private ProductRepo productRepo;
	private CategoryRepo categoryRepo;
	private ModelMapper modelMapper;
	
//	Performing a constructor injection
	public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProductDto createProducts(ProductDto product, long categoryId) {
		Product createProduct = MapDtoToEntity(product);
		Category findcategory = categoryRepo.findById(categoryId).orElseThrow(()->new ResponseNotFoundException("category with " + categoryId + " not found"));
		createProduct.setCategory(findcategory);
		
		Product saveProduct = productRepo.save(createProduct);
		
		ProductDto createProductDto = MapEntityToDto(saveProduct);
		
		return createProductDto;
	}

	private ProductDto MapEntityToDto(Product saveProduct) {
		
//		Map class Product to ProductDto
		ProductDto createProductDto = modelMapper.map(saveProduct, ProductDto.class);		
		CategoryDto getCategories = new CategoryDto();
		getCategories.setId(saveProduct.getCategory().getId());
		getCategories.setDescription(saveProduct.getCategory().getDescription());
		getCategories.setName(saveProduct.getCategory().getName());
		createProductDto.setCategory(getCategories);
		
		return createProductDto;
	}

	private Product MapDtoToEntity(ProductDto productDto) {
//		Map class ProductDto to Product
		Product createProduct = modelMapper.map(productDto, Product.class);
		return createProduct;
	}

	@Override
	public List<ProductDto> getAllProducts(long categoryId) {
		List<Product> product = productRepo.findByCategoryId(categoryId);
		List<ProductDto> productDto = product.stream().map(productDtoMap->MapEntityToDto(productDtoMap)).collect(Collectors.toList());
		
		
		return productDto;
	}
	
//	kept Dry principle by refractring the similar methods in MethodExceptionHandeling
	private Product MethodExceptionHandling(long categoryId, long productId) {
		Category findcategory = categoryRepo.findById(categoryId).orElseThrow(()->new ResponseNotFoundException("category with " + categoryId + " not found"));
		Product findProduct = productRepo.findById(productId).orElseThrow(()-> new ResponseNotFoundException("Product with " + productId + " not found"));
		if(!findProduct.getCategory().getId().equals(findcategory.getId())){
			throw new ResponseNotFoundException("Not found");
		}
		return findProduct;
	}

	@Override
	public ProductDto getProductById(long categoryId, long productId) {
		Product findProduct = MethodExceptionHandling(categoryId, productId);	
		return MapEntityToDto(findProduct);
	}


	@Override
	public ProductDto updateProductById(long categoryId, long productId, ProductDto product) {
		Product findProduct = MethodExceptionHandling(categoryId, productId);
		findProduct.setName(product.getName());
		findProduct.setPrice(product.getPrice());
		Product saveProduct = productRepo.save(findProduct);
		return MapEntityToDto(saveProduct);
	}

	@Override
	public void deleteProductById(long categoryId, long productId) {
		Product findProduct = MethodExceptionHandling(categoryId, productId);
		
		productRepo.delete(findProduct);
		
	}
	   @Override
	    public List<ProductDto> searchProducts(Long categoryId, Double price) {
	        List<Product> products = productRepo.findByCategoryAndPrice(categoryId, price);
	        return products.stream()
	                       .map(this::MapEntityToDto)
	                       .collect(Collectors.toList());
	    }

}

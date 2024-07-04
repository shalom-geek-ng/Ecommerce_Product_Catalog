package com.PrunnyProductCatalog.EcommerceProductCatalog.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.ProductDto;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	private ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("category/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(@PathVariable long categoryId, @RequestBody @Valid ProductDto productDto){
		return new ResponseEntity<ProductDto>(productService.createProducts(productDto, categoryId), HttpStatus.CREATED);
	}
	@GetMapping("category/{categoryId}/products")
	public List<ProductDto> getProducts(@PathVariable long categoryId){
		return productService.getAllProducts(categoryId);
	}
	@GetMapping("category/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable long categoryId, @PathVariable long productId){
		return new ResponseEntity<ProductDto>(productService.getProductById(categoryId, productId),HttpStatus.OK);
	}
	@PutMapping("category/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> updateProductById(@PathVariable long categoryId, @PathVariable long productId,@RequestBody ProductDto productDto ){
		{
			return new ResponseEntity<ProductDto>(productService.updateProductById(categoryId, productId, productDto), HttpStatus.OK);
		}
	}
	@DeleteMapping("category/{categoryId}/products/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable long categoryId, @PathVariable long productId){
		productService.deleteProductById(categoryId, productId);
		return new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
	}
	 @GetMapping("/products/search")
	    public ResponseEntity<List<ProductDto>> searchProducts(
	        @RequestParam(value = "categoryId", required = false) Long categoryId,
	        @RequestParam(value = "price", required = false) Double price) {
	        
	        List<ProductDto> products = productService.searchProducts(categoryId, price);
	        return ResponseEntity.ok(products);
	    }
	
}

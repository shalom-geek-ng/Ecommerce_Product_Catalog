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
import org.springframework.web.bind.annotation.RestController;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.CategoryDto;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
//	Performing looselycoupled injection
	private CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<CategoryDto> getCategories(){
		return categoryService.getCategories();
	}
	@GetMapping("/{CategoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long CategoryId){
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(CategoryId), HttpStatus.OK);
	}
	
	@PutMapping("/{CategoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable long CategoryId, @RequestBody CategoryDto categoryDto){
		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(CategoryId, categoryDto),HttpStatus.OK);
	}
	@DeleteMapping("/{CategoryId}")
	public void deleteCategoryById(@PathVariable long CategoryId) {
		 categoryService.deleteCategoryById(CategoryId);
	}
}

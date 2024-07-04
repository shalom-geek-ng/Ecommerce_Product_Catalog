package com.PrunnyProductCatalog.EcommerceProductCatalog.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Category;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Exceptions.ResponseNotFoundException;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Payload.CategoryDto;
import com.PrunnyProductCatalog.EcommerceProductCatalog.Repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepo categoryRepo;
	private ModelMapper modelMapper;
	public CategoryServiceImpl(CategoryRepo categoryRepo,ModelMapper modelMapper) {
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = MapDtoToEntity(categoryDto);
		
		Category savedCategory = categoryRepo.save(category);
		
		CategoryDto categoryDtoObject = MapEntityToDto(savedCategory);
		return categoryDtoObject;
	}

	private CategoryDto MapEntityToDto(Category savedCategory) {
//		map Category class into CategoryDto
		CategoryDto categoryDtoObject = modelMapper.map(savedCategory, CategoryDto.class);
		return categoryDtoObject;
	}

	private Category MapDtoToEntity(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map(categoryDto -> 
		MapEntityToDto(categoryDto)).collect(Collectors.toList());
		}

	@Override
	public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResponseNotFoundException("category with " + categoryId + " not found"));
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		Category savedCategory = categoryRepo.save(category);
		
		return MapEntityToDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResponseNotFoundException("category with " + categoryId + " not found"));
		return MapEntityToDto(category);
	}

	@Override
	public void deleteCategoryById(long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResponseNotFoundException("category with " + categoryId + " not found"));
		categoryRepo.delete(category);
		
	}

}

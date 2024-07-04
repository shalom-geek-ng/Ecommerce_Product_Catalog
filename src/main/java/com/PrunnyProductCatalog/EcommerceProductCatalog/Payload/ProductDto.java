package com.PrunnyProductCatalog.EcommerceProductCatalog.Payload;

import com.PrunnyProductCatalog.EcommerceProductCatalog.Entities.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
	private Long id;
	@NotNull(message = "name cannot be null")
	@Size(min=3, message = "name must be more than 3 characters")
	private String name;
	@NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than 0")
	private double price;
	private CategoryDto category;
}

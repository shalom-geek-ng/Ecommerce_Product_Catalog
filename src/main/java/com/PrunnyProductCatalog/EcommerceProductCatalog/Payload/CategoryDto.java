package com.PrunnyProductCatalog.EcommerceProductCatalog.Payload;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
	private Long id;
	@NotNull(message = "Must not be null")
	@Size(min=3, message="Name must be more than 3")
	private String name;
	@NotNull(message = "Must not be null")
	@Size(min=3, message="Name must be more than 3")
	private String description;
	
}

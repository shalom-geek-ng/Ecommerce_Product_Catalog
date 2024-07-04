# Ecommerce Product Catalog

## Project Description
The E-commerce Product Catalog project is a service designed for an e-commerce platform that allows CRUD operations for products and categories. This service provides endpoints for adding, retrieving, updating, and deleting products and categories, as well as searching and filtering capabilities.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (or any other relational database)
- ModelMapper
- Lombok

## Requirements
- Implement endpoints for adding, retrieving, updating, and deleting products and categories.
- Use a relational database to store product and category data.
- Implement searching and filtering capabilities (e.g., by category, price range).
- Use Spring Data JPA for database interactions.
- Implement basic validation and error handling.
- Create a comprehensive README with setup instructions and API documentation.

## Setup Instructions

### Prerequisites
- Java 8 or higher
- Maven
- MySQL (or any other relational database)

### Steps to Setup
1. Clone the repository
   ```sh
   git clone https://github.com/yourusername/EcommerceProductCatalog.git
   cd EcommerceProductCatalog
## Configuration

To configure the database, update the `application.properties` file located in `src/main/resources/` with the following properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_product_catalog
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

ecommerce_product_catalog
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── PrunnyProductCatalog
│   │   │           └── EcommerceProductCatalog
│   │   │               ├── Controller
│   │   │               │   ├── CategoryController.java
│   │   │               │   └── ProductController.java
│   │   │               ├── Entities
│   │   │               │   ├── Category.java
│   │   │               │   └── Product.java
│   │   │               ├── Exceptions
│   │   │               │   ├── ResponseControllerAdvice.java
│   │   │               │   ├── ResponseMessage.java
│   │   │               │   └── ResponseNotFoundException.java
│   │   │               ├── Payload
│   │   │               │   ├── CategoryDto.java
│   │   │               │   └── ProductDto.java
│   │   │               ├── Repository
│   │   │               │   ├── CategoryRepo.java
│   │   │               │   └── ProductRepo.java
│   │   │               ├── Service
│   │   │               │   ├── CategoryService.java
│   │   │               │   ├── CategoryServiceImpl.java
│   │   │               │   ├── ProductService.java
│   │   │               │   └── ProductServiceImpl.java
│   │   │               └── EcommerceProductCatalogApplication.java
│   │   └── resources
│   │       └── application.properties
└── README.txt


## Project Structure

### Controller

- **`CategoryController.java`**: Contains endpoints related to categories.
- **`ProductController.java`**: Contains endpoints related to products.

### Entities

- **`Category.java`**: Entity class representing a category.
- **`Product.java`**: Entity class representing a product.

### Exceptions

- **`ResponseControllerAdvice.java`**: Global exception handler for custom error responses.
- **`ResponseMessage.java`**: Response message structure for exceptions.
- **`ResponseNotFoundException.java`**: Custom exception for resource not found.

### Payload

- **`CategoryDto.java`**: Data transfer object for category data.
- **`ProductDto.java`**: Data transfer object for product data.

### Repository

- **`CategoryRepo.java`**: Interface for category repository using Spring Data JPA.
- **`ProductRepo.java`**: Interface for product repository using Spring Data JPA.

### Service

- **`CategoryService.java`**: Interface defining category service methods.
- **`CategoryServiceImpl.java`**: Implementation of category service methods.
- **`ProductService.java`**: Interface defining product service methods.
- **`ProductServiceImpl.java`**: Implementation of product service methods.

### Main Application Class

- **`EcommerceProductCatalogApplication.java`**: Main class to bootstrap the Spring Boot application.

### Resources

- **`application.properties`**: Configuration file for database connection and other properties.



## API Documentation


##EndPoints

### Category Endpoints(Having a One to many Relationship with Products)

#### Create a Category
- **URL:** `/api/v1/category`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "Electronics",
    "description": "Electronic items"
  }

#### Retrieve a Category
- **URL:** `/api/v1/category`
- **Method:** `GET`
- **Request Body:**
  ```json
  {
    "name": "Electronics",
    "description": "Electronic items"
  }

#### Retrieve one Category
- **URL:** `/api/v1/category/{CategoryId}`
- **Method:** `GET`
- **Request Body:**
  ```json
  {
    "name": "Electronics",
    "description": "Electronic items"
  }

#### Delete one Category
- **URL:** `/api/v1/category/{CategoryId}`
- **Method:** `DELETE`
- **Request Body:**


#### Update Category
- **URL:** `/api/v1/category/{CategoryId}`
- **Method:** `PUT`
- **Request Body:**


#### `ProductController.java`

This controller manages the product-related endpoints for the API. It allows for the creation, retrieval, update, and deletion of products, as well as searching products by category and price.

**Key Methods:**

- **`POST /api/v1/category/{categoryId}/products`**: Create a new product for a specific category.
  - **Request Body:** `ProductDto` object containing product details.
  - **Response:** `ProductDto` object with the created product.

- **`GET /api/v1/category/{categoryId}/products`**: Retrieve all products for a specific category.
  - **Response:** List of `ProductDto` objects.

- **`GET /api/v1/category/{categoryId}/products/{productId}`**: Retrieve a product by its ID within a specific category.
  - **Response:** `ProductDto` object with the product details.

- **`PUT /api/v1/category/{categoryId}/products/{productId}`**: Update a product by its ID within a specific category.
  - **Request Body:** `ProductDto` object with updated product details.
  - **Response:** `ProductDto` object with the updated product.

- **`DELETE /api/v1/category/{categoryId}/products/{productId}`**: Delete a product by its ID within a specific category.
  - **Response:** Success message with HTTP status OK.

- **`GET /api/v1/products/search`**: Search for products based on category ID and/or price.
  - **Query Parameters:**
    - `categoryId` (optional): Filter products by category ID.
    - `price` (optional): Filter products by price.
  - **Response:** List of `ProductDto` objects matching the search criteria.

**Code Example:**

```java
@RestController
@RequestMapping("/api/v1/")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping("category/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable long categoryId, @RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.createProducts(productDto, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("category/{categoryId}/products")
    public List<ProductDto> getProducts(@PathVariable long categoryId){
        return productService.getAllProducts(categoryId);
    }

    @GetMapping("category/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long categoryId, @PathVariable long productId){
        return new ResponseEntity<>(productService.getProductById(categoryId, productId), HttpStatus.OK);
    }

    @PutMapping("category/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable long categoryId, @PathVariable long productId, @RequestBody ProductDto productDto ){
        return new ResponseEntity<>(productService.updateProductById(categoryId, productId, productDto), HttpStatus.OK);
    }

    @DeleteMapping("category/{categoryId}/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable long categoryId, @PathVariable long productId){
        productService.deleteProductById(categoryId, productId);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
        @RequestParam(value = "categoryId", required = false) Long categoryId,
        @RequestParam(value = "price", required = false) Double price) {
        List<ProductDto> products = productService.searchProducts(categoryId, price);
        return ResponseEntity.ok(products);
    }
}




package mx.edtecdesoftware.edu.mx.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edtecdesoftware.edu.mx.demo.domain.repository.ProductRepository;
import mx.edtecdesoftware.edu.mx.demo.domain.service.Product;
import mx.edtecdesoftware.edu.mx.demo.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Manage products in the store.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products."
    )

    @ApiResponse(responseCode = "200", description = "Successful retrieval")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.getAll();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Return a product ny its ID if it exists"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error ")

    public ResponseEntity<Product> getById(
            @Parameter(description = "ID of the product retrieved", example = "7", required = true)
            @PathVariable("id") int productId){
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
            }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get a product by category",
            description = "Return all products in a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Product found in the category")
    @ApiResponse(responseCode = "404", description = "Product not found in the category")
    @ApiResponse(responseCode = "500", description = "Internal server error")

    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "ID of the category retrieved", example = "7", required = true)
            @PathVariable("categoryId") int categoryId) {
        Optional<List<Product>> productsOptional = productService.getByCategory(categoryId);
        if (productsOptional.isPresent() && !productsOptional.get().isEmpty()) {
            List<Product> products = productsOptional.get();
            if (products.size() == 1 && products.getFirst() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(
            summary = "Save a new product",
            description = "Register a new product and return the created product.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example product",
                                    value = """
                                          {
                                          "name" : "Mirinda",
                                          "categoryId" : "5",
                                          "price" : "20.50",
                                          "stock" : 300,
                                          "active" : true
                                          
                                          }
                                         
"""
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID",
            description = "Delect a product if it exists")
    @ApiResponse(responseCode = "201", description = "Product deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the product to be deleted")
            @PathVariable("id") int productId){
        if (productService.delete(productId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
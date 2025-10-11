package com.znaji.InventoryManagementSystem.controller;

import com.znaji.InventoryManagementSystem.dto.request.ProductRequest;
import com.znaji.InventoryManagementSystem.dto.response.ProductResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam("q") String query) {
        if (query == null || query.isBlank()) {
            return productService.getAllProducts();
        }
        return productService.searchProducts(query);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Response createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response updateProduct(@PathVariable Long id,
                                  @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN')")
    public Response uploadProductImage(@PathVariable Long id,
                                       @RequestParam("image") MultipartFile image) {
        return productService.uploadProductImage(id, image);
    }
}

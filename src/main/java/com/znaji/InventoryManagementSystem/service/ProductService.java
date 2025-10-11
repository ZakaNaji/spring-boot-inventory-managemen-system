package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.ProductRequest;
import com.znaji.InventoryManagementSystem.dto.response.ProductResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Response createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    Response updateProduct(Long id, ProductRequest request);

    Response deleteProduct(Long id);

    List<ProductResponse> searchProducts(String input);

    Response uploadProductImage(Long id, MultipartFile image);
}

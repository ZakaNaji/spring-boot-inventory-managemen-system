package com.znaji.InventoryManagementSystem.repository;

import com.znaji.InventoryManagementSystem.dto.response.ProductResponse;
import com.znaji.InventoryManagementSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductResponse> findAllBy();

    Optional<ProductResponse> findProductById(Long id);

    List<ProductResponse> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    Optional<Product> findBySkuIgnoreCase(String sku);
}

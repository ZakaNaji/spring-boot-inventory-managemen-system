package com.znaji.InventoryManagementSystem.repository;

import com.znaji.InventoryManagementSystem.dto.response.ProductView;
import com.znaji.InventoryManagementSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<ProductView> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
package com.znaji.InventoryManagementSystem.repository;

import com.znaji.InventoryManagementSystem.dto.response.CategoryResponse;
import com.znaji.InventoryManagementSystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

    List<CategoryResponse> findAllBy();

    Optional<CategoryResponse> findCategoryById(Long id);
}
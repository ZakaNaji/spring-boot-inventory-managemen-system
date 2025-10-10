package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.CategoryRequest;
import com.znaji.InventoryManagementSystem.dto.response.CategoryResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;

import java.util.List;

public interface CategoryService {

    Response createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    Response updateCategory(Long id, CategoryRequest request);

    Response deleteCategory(Long id);
}

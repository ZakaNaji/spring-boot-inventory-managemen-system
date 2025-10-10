package com.znaji.InventoryManagementSystem.service.impl;

import com.znaji.InventoryManagementSystem.dto.request.CategoryRequest;
import com.znaji.InventoryManagementSystem.dto.response.CategoryResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.entity.Category;
import com.znaji.InventoryManagementSystem.exception.NotFoundException;
import com.znaji.InventoryManagementSystem.repository.CategoryRepository;
import com.znaji.InventoryManagementSystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response createCategory(CategoryRequest request) {
        Category newCategory = new Category();
        newCategory.setName(request.name());
        categoryRepository.save(newCategory);

        return Response.builder()
                .status(HttpStatus.CREATED.value())
                .message("Category created.")
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllBy();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new NotFoundException("Category not found for id: " + id));
    }

    @Override
    public Response updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository
                .findById(id).orElseThrow(() -> new NotFoundException("Category not found for id: " + id));

        category.setName(request.name());
        categoryRepository.save(category);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Category updated")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found for id: " + id));
        categoryRepository.deleteById(id);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Category deleted")
                .build();
    }
}

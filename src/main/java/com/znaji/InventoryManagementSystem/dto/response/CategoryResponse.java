package com.znaji.InventoryManagementSystem.dto.response;

import com.znaji.InventoryManagementSystem.entity.Category;

/**
 * Projection for {@link Category}
 */
public interface CategoryResponse {
    Long getId();

    String getName();
}
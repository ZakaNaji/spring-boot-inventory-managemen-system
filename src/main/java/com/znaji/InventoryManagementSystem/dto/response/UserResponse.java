package com.znaji.InventoryManagementSystem.dto.response;

import com.znaji.InventoryManagementSystem.entity.User;
import com.znaji.InventoryManagementSystem.entity.UserRole;

/**
 * Projection for {@link com.znaji.InventoryManagementSystem.entity.User}
 */
public interface UserResponse {
    Long getId();

    String getName();

    String getEmail();

    String getPhoneNumber();

    UserRole getRole();

}
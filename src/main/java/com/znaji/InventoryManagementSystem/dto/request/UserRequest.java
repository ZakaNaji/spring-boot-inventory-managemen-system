package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.UserRole;

import java.io.Serializable;

public record UserRequest(
        String email,
        String phoneNumber,
        UserRole role,
        String name) implements Serializable {
}

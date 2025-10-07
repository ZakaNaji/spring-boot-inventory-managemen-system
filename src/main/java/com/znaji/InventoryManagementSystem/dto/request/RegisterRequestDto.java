package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(@NotBlank String name, @NotBlank String email, @NotBlank String password, @NotNull
                                 UserRole userRole) {
}

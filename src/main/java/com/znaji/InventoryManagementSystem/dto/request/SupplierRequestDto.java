package com.znaji.InventoryManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.znaji.InventoryManagementSystem.entity.Supplier}
 */
public record SupplierRequestDto(@NotBlank String name, @NotBlank String contactInfo, String address) implements Serializable {
}
package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.Supplier;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Supplier}
 */
public record SupplierRequest(
        @NotBlank String name,
        @NotBlank String contactInfo,
        String address
) implements Serializable {
}

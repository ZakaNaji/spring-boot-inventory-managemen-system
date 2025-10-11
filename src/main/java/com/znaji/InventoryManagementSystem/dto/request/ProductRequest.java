package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.Product;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link Product}
 */
public record ProductRequest(
        @NotBlank String name,
        @NotBlank String sku,
        @NotNull @Positive BigDecimal price,
        @PositiveOrZero int stockQuantity,
        String description,
        String imageUrl,
        @FutureOrPresent LocalDateTime expiryDate,
        Long categoryId
) implements Serializable {
}

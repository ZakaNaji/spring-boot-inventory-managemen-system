package com.znaji.InventoryManagementSystem.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.znaji.InventoryManagementSystem.entity.Product}
 */
public record ProductRequestDto(@NotBlank String name, @NotBlank String sku, @Positive BigDecimal price,
                                @PositiveOrZero int stockQuantity, String description, String imageUrl,
                                @FutureOrPresent LocalDateTime expiryDate) implements Serializable {
}
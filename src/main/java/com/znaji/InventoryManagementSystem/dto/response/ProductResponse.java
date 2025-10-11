package com.znaji.InventoryManagementSystem.dto.response;

import com.znaji.InventoryManagementSystem.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Projection for {@link Product}
 */
public interface ProductResponse {
    Long getId();

    String getName();

    String getSku();

    BigDecimal getPrice();

    int getStockQuantity();

    String getDescription();

    String getImageUrl();

    LocalDateTime getExpiryDate();

    LocalDateTime getCreatedAt();
}

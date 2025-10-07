package com.znaji.InventoryManagementSystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductView {

    public String getName();

    public String getDescription();

    Long getId();

    String getSku();

    BigDecimal getPrice();

    int getStockQuantity();

    String getImageUrl();

    LocalDateTime getExpiryDate();

    LocalDateTime getCreatedAt();
}

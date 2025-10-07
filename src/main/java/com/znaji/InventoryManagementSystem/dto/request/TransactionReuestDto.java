package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.Transaction;
import com.znaji.InventoryManagementSystem.entity.TransactionStatus;
import com.znaji.InventoryManagementSystem.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Transaction}
 */
public record TransactionReuestDto(@Positive int totalProducts, @Positive BigDecimal totalPrice,
                             @NotNull TransactionType transactionType, @NotNull TransactionStatus transactionStatus,
                             String description, String note) implements Serializable {
}
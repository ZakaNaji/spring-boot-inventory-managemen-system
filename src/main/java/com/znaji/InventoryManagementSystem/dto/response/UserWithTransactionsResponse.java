package com.znaji.InventoryManagementSystem.dto.response;

import com.znaji.InventoryManagementSystem.entity.TransactionStatus;
import com.znaji.InventoryManagementSystem.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Projection for {@link com.znaji.InventoryManagementSystem.entity.User}
 */
public interface UserWithTransactionsResponse {
    Long getId();

    String getName();

    String getEmail();

    List<TransactionInfo> getTransactions();

    /**
     * Projection for {@link com.znaji.InventoryManagementSystem.entity.Transaction}
     */
    interface TransactionInfo {
        Long getId();

        int getTotalProducts();

        BigDecimal getTotalPrice();

        TransactionType getTransactionType();

        TransactionStatus getTransactionStatus();

        String getDescription();

        String getNote();

        LocalDateTime getCreatedAt();

        LocalDateTime getUpdatedAt();
    }
}
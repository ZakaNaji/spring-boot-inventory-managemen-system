package com.znaji.InventoryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    PURCHASE,SALE,RETURN_TO_SUPPLIER;

    @JsonCreator
    public static TransactionType from(String value) {
        if (value == null) return null;
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + value);
        }
    }
}

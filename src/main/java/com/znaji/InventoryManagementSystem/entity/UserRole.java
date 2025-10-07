package com.znaji.InventoryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN,MANAGER,USER;

    @JsonCreator
    public static UserRole from(String value) {
        if (value == null) return null;
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + value);
        }
    }
}

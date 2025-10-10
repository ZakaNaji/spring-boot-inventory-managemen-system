package com.znaji.InventoryManagementSystem.dto.response;

import com.znaji.InventoryManagementSystem.entity.Supplier;

/**
 * Projection for {@link Supplier}
 */
public interface SupplierResponse {
    Long getId();

    String getName();

    String getContactInfo();

    String getAddress();
}

package com.znaji.InventoryManagementSystem.dto.response;

import lombok.Builder;

@Builder
public record Response(
        int status,
        String message
) {
}

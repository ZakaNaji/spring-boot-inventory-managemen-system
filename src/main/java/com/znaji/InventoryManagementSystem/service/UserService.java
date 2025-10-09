package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);
}

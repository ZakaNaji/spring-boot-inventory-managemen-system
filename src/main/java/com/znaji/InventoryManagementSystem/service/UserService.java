package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);

    List<UserResponse> getAllUsers();

    UserResponse getCurrentLoggedInUser();

    UserResponse getUserById(Long id);
}

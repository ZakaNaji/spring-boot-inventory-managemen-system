package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.request.UserRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);

    List<UserResponse> getAllUsers();

    UserResponse getCurrentLoggedInUser();

    UserResponse getUserById(Long id);

    Response updateUser(Long id, UserRequest request);

    Response deleteUser(Long id);
}

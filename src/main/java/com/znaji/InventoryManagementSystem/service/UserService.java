package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.LoginRequest;
import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.request.UserRequest;
import com.znaji.InventoryManagementSystem.dto.response.AuthResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.UserResponse;
import com.znaji.InventoryManagementSystem.dto.response.UserWithTransactionsResponse;

import java.util.List;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getCurrentLoggedInUser();

    UserResponse getUserById(Long id);

    Response updateUser(Long id, UserRequest request);

    Response deleteUser(Long id);

    UserWithTransactionsResponse getUserTransactions(Long id);
}

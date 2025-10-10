package com.znaji.InventoryManagementSystem.controller;

import com.znaji.InventoryManagementSystem.dto.request.LoginRequest;
import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.response.AuthResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.security.JwtUtils;
import com.znaji.InventoryManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Response register(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }
}

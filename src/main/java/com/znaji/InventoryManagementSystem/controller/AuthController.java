package com.znaji.InventoryManagementSystem.controller;

import com.znaji.InventoryManagementSystem.dto.request.LoginRequestDto;
import com.znaji.InventoryManagementSystem.dto.response.AuthResponse;
import com.znaji.InventoryManagementSystem.security.AuthUser;
import com.znaji.InventoryManagementSystem.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        ));

        AuthUser principal = (AuthUser) authenticate.getPrincipal();
        String token = jwtUtils.generateToken(principal);
        return new AuthResponse(token);
    }
}

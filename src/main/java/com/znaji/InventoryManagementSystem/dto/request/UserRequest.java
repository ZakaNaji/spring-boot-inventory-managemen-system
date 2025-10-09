package com.znaji.InventoryManagementSystem.dto.request;

import com.znaji.InventoryManagementSystem.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record UserRequest(
        @NotBlank(message = "email can't be empty")
        @Email(message = "Not a valid email")
        String email,
        @NotBlank(message = "Password can't be empty")
        String password,
        @NotBlank(message = "Phone number can't be empty")
        @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
        String phoneNumber,
        @NotNull
        UserRole role,
        @NotBlank
        String name) implements Serializable {
}

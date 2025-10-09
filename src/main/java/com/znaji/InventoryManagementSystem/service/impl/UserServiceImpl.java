package com.znaji.InventoryManagementSystem.service.impl;

import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.entity.User;
import com.znaji.InventoryManagementSystem.exception.BusinessException;
import com.znaji.InventoryManagementSystem.repository.UserRepository;
import com.znaji.InventoryManagementSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response registerUser(RegisterRequest request) {
        String email = request.email();
        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(email);
        if (existingUser.isPresent()) {
            throw new BusinessException(String.format("User with email %s exists already", email));
        }

        User newUser = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.userRole())
                .build();
        userRepository.save(newUser);
        return Response.builder()
                .status(HttpStatus.CREATED.value())
                .message(String.format("User with email %s was created.", email))
                .build();
    }
}

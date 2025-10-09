package com.znaji.InventoryManagementSystem.service.impl;

import com.znaji.InventoryManagementSystem.dto.request.RegisterRequest;
import com.znaji.InventoryManagementSystem.dto.request.UserRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.UserResponse;
import com.znaji.InventoryManagementSystem.entity.User;
import com.znaji.InventoryManagementSystem.exception.BusinessException;
import com.znaji.InventoryManagementSystem.exception.NotFoundException;
import com.znaji.InventoryManagementSystem.repository.UserRepository;
import com.znaji.InventoryManagementSystem.security.AuthUser;
import com.znaji.InventoryManagementSystem.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
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

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllBy(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public UserResponse getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new NotFoundException("No logged in user currently");
        }

        return userRepository.findUserByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("No logged in user currently"));

    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("no user found with id: " + id));
    }

    @Override
    public Response updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no user found with id: " + id));

        //set new values:
        user.setName(request.name());
        user.setEmail(request.email());
        user.setRole(request.role());
        user.setPhoneNumber(request.phoneNumber());

        userRepository.save(user);
        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("User updated.")
                .build();
    }
}

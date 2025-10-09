package com.znaji.InventoryManagementSystem.repository;

import com.znaji.InventoryManagementSystem.dto.response.UserResponse;
import com.znaji.InventoryManagementSystem.dto.response.UserWithTransactionsResponse;
import com.znaji.InventoryManagementSystem.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailIgnoreCase(String email);

  List<UserResponse> findAllBy(Sort sort);

  Optional<UserResponse> findUserByEmail(String email);

  Optional<UserResponse> findUserById(Long id);

  Optional<User> findById(Long id);

  Optional<UserWithTransactionsResponse> findUserWithTransactionByEmail(String email);
}
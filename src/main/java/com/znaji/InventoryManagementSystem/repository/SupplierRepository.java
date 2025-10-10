package com.znaji.InventoryManagementSystem.repository;

import com.znaji.InventoryManagementSystem.dto.response.SupplierResponse;
import com.znaji.InventoryManagementSystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<SupplierResponse> findAllBy();

    Optional<SupplierResponse> findSupplierById(Long id);
}

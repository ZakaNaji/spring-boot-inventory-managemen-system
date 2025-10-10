package com.znaji.InventoryManagementSystem.controller;

import com.znaji.InventoryManagementSystem.dto.request.SupplierRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.SupplierResponse;
import com.znaji.InventoryManagementSystem.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierResponse getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Response createSupplier(@Valid @RequestBody SupplierRequest request) {
        return supplierService.createSupplier(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response updateSupplier(@PathVariable Long id,
                                   @Valid @RequestBody SupplierRequest request) {
        return supplierService.updateSupplier(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response deleteSupplier(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }
}

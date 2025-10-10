package com.znaji.InventoryManagementSystem.service.impl;

import com.znaji.InventoryManagementSystem.dto.request.SupplierRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.SupplierResponse;
import com.znaji.InventoryManagementSystem.entity.Supplier;
import com.znaji.InventoryManagementSystem.exception.NotFoundException;
import com.znaji.InventoryManagementSystem.repository.SupplierRepository;
import com.znaji.InventoryManagementSystem.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Response createSupplier(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.name());
        supplier.setContactInfo(request.contactInfo());
        supplier.setAddress(request.address());

        supplierRepository.save(supplier);

        return Response.builder()
                .status(HttpStatus.CREATED.value())
                .message("Supplier created.")
                .build();
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAllBy();
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {
        return supplierRepository.findSupplierById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found for id: " + id));
    }

    @Override
    public Response updateSupplier(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found for id: " + id));

        supplier.setName(request.name());
        supplier.setContactInfo(request.contactInfo());
        supplier.setAddress(request.address());

        supplierRepository.save(supplier);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Supplier updated.")
                .build();
    }

    @Override
    public Response deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found for id: " + id));

        supplierRepository.delete(supplier);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Supplier deleted.")
                .build();
    }
}

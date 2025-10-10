package com.znaji.InventoryManagementSystem.service;

import com.znaji.InventoryManagementSystem.dto.request.SupplierRequest;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    Response createSupplier(SupplierRequest request);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse getSupplierById(Long id);

    Response updateSupplier(Long id, SupplierRequest request);

    Response deleteSupplier(Long id);
}

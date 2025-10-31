package com.pharmatrack.service;

import com.pharmatrack.dto.SupplierRequestDTO;
import com.pharmatrack.dto.SupplierResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SupplierService {
    SupplierResponseDTO create(SupplierRequestDTO dto);
    SupplierResponseDTO getById(UUID id);
    Page<SupplierResponseDTO> search(String q, Pageable pageable);
    SupplierResponseDTO update(UUID id, SupplierRequestDTO dto);
    void delete(UUID id);

    List<SupplierResponseDTO> getAllSuppliers();
}

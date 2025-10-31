package com.pharmatrack.service.impl;

import com.pharmatrack.dto.SupplierRequestDTO;
import com.pharmatrack.dto.SupplierResponseDTO;
import com.pharmatrack.entity.Supplier;
import com.pharmatrack.enums.SupplierStatus;
import com.pharmatrack.mapper.SupplierMapper;
import com.pharmatrack.repository.SupplierRepository;
import com.pharmatrack.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public SupplierResponseDTO create(SupplierRequestDTO dto) {
        Supplier entity = mapper.toEntity(dto);
        if (dto.getStatus() != null) {
            try {
                entity.setStatus(SupplierStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException ignored) {
            }
        }
        Supplier saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponseDTO getById(UUID id) {
        Supplier s = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
        return mapper.toDto(s);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupplierResponseDTO> search(String q, Pageable pageable) {
        Page<Supplier> page = repository
                .findByNameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrContactContaining(
                        q, q, q, pageable);
        return page.map(mapper::toDto);
    }

    @Override
    public SupplierResponseDTO update(UUID id, SupplierRequestDTO dto) {
        Supplier s = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
        mapper.updateSupplierFromDto(dto, s);
        if (dto.getStatus() != null) {
            try {
                s.setStatus(SupplierStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException ignored) {
            }
        }
        Supplier updated = repository.save(s);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Supplier not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}

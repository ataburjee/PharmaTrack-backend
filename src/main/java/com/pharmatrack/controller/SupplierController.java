package com.pharmatrack.controller;

import com.pharmatrack.dto.ColumnDTO;
import com.pharmatrack.dto.SupplierRequestDTO;
import com.pharmatrack.dto.SupplierResponseDTO;
import com.pharmatrack.entity.Supplier;
import com.pharmatrack.service.ColumnService;
import com.pharmatrack.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pharmatrack/suppliers")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;
    private final ColumnService columnService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(@Valid @RequestBody SupplierRequestDTO dto) {
        UUID uuid = UUID.randomUUID();
        dto.setAddedBy(uuid);
        dto.setUpdatedBy(uuid);
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/columns")
    public List<ColumnDTO> getSupplierColumns() {
        return columnService.getColumnsForEntity(Supplier.class, List.of("id", "address", "updatedAt", "updatedBy"));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> fetchSuppliers () {
        return ResponseEntity.ok(service.getAllSuppliers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable UUID id,
                                                      @Valid @RequestBody SupplierRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Supplier deleted successfully");
    }
}

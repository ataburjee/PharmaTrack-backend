package com.pharmatrack.service;

import com.pharmatrack.dto.ColumnDTO;
import com.pharmatrack.dto.MedicineDTO;
import com.pharmatrack.entity.Medicine;
import com.pharmatrack.mapper.MedicineMapper;
import com.pharmatrack.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository repository;
    private final MedicineMapper mapper;
    private final CategoryService categoryService;
    private final ColumnService columnService;

    public List<MedicineDTO> getAllMedicines() {
        return repository.findAll()
                .stream()
                .map(medicine -> {
                    MedicineDTO dto = mapper.toDTO(medicine);
                    dto.setCategory(categoryService.getCategoryNameById(medicine.getCategoryId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public MedicineDTO getMedicine(UUID id) {
        return repository.findById(id).map(mapper::toDTO).orElse(null);
    }

    public MedicineDTO createMedicine(MedicineDTO dto) {
        dto.setCategoryId(categoryService.getCategoryIdByName(dto.getCategory()));
        Medicine med = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(med);
    }

    public MedicineDTO updateMedicine(UUID id, MedicineDTO dto) {
        return repository.findById(id)
                .map(med -> {
                    med.setName(dto.getName());
                    med.setCategoryId(dto.getCategoryId());
                    med.setBrand(dto.getBrand());
                    med.setQuantity(dto.getQuantity());
                    med.setExpiry(dto.getExpiry());
                    return mapper.toDTO(repository.save(med));
                }).orElse(null);
    }

    public void deleteMedicine(UUID id) {
        repository.deleteById(id);
    }

    public List<ColumnDTO> getColumns() {
        List<ColumnDTO> columns = columnService.getColumnsForEntity(Medicine.class,
                List.of("createdAt", "updatedAt", "updatedBy"));
        return columns.stream().peek(col -> {
            if(col.getAccessorKey().equals("categoryId")) {
                col.setAccessorKey("category");
                col.setHeader("Category");
            }
        }).toList();
    }
}

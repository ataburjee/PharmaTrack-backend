package com.pharmatrack.controller;

import com.pharmatrack.dto.ColumnDTO;
import com.pharmatrack.dto.MedicineDTO;
import com.pharmatrack.entity.Medicine;
import com.pharmatrack.entity.Order;
import com.pharmatrack.service.ColumnService;
import com.pharmatrack.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pharmatrack/medicines")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService service;

    @GetMapping
    public List<MedicineDTO> getAll() {
        return service.getAllMedicines();
    }

    @GetMapping("/{id}")
    public MedicineDTO get(@PathVariable UUID id) {
        return service.getMedicine(id);
    }

    @PostMapping
    public MedicineDTO create(@RequestBody MedicineDTO dto) {
        System.out.println("MedicineDTO = " + dto);
        return service.createMedicine(dto);
    }

    @GetMapping("/columns")
    public List<ColumnDTO> getMedicineColumns() {
        return service.getColumns();
    }

    @PutMapping("/{id}")
    public MedicineDTO update(@PathVariable UUID id, @RequestBody MedicineDTO dto) {
        return service.updateMedicine(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteMedicine(id);
    }
}

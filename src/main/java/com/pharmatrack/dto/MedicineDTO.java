package com.pharmatrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDTO {
    private UUID id;
    private String name;
    private Long categoryId;
    private String category;
    private String brand;
    private Integer quantity;
    private Long price;
    private LocalDate expiry;
}

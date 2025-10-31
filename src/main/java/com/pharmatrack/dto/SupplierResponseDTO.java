package com.pharmatrack.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String status;
    private OffsetDateTime createdAt;
    private UUID addedBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}

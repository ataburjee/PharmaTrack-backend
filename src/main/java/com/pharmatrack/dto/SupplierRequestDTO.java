package com.pharmatrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequestDTO {
    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @Size(max = 30)
    private String contact;

    @Size(max = 255)
    private String address;

    private String status;

    private UUID addedBy;

    private UUID updatedBy;
}

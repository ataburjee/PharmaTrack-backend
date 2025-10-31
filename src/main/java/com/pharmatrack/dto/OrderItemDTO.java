package com.pharmatrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private UUID id;

    private UUID medicineId;
    private String medicineName;  //To show at UI

    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}

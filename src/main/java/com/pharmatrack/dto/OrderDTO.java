package com.pharmatrack.dto;

import com.pharmatrack.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;

    private UUID supplierId;
    private String supplierName;   // To show at UI

    private List<OrderItemDTO> orderItems;

    private Double totalAmount;
    private OrderStatus status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.pharmatrack.service.impl;

import com.pharmatrack.dto.OrderDTO;
import com.pharmatrack.entity.*;
import com.pharmatrack.mapper.OrderMapper;
import com.pharmatrack.repository.*;
import com.pharmatrack.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final MedicineRepository medicineRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = orderMapper.toEntity(dto);

        // Fetch Supplier entity
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        order.setSupplier(supplier);

        // Map medicine references from DTO to Entity
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            OrderItem item = order.getOrderItems().get(i);
            UUID medicineId = dto.getOrderItems().get(i).getMedicineId(); // From DTO
            Medicine medicine = medicineRepository.findById(medicineId)
                    .orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
            item.setMedicine(medicine);
            item.setOrder(order);
            item.setTotalPrice(item.getUnitPrice()* item.getQuantity());
            // totalPrice is auto-calculated via @PrePersist
        }

        // Calculate total amount
        order.setTotalAmount(order.getOrderItems()
                .stream().mapToDouble(OrderItem::getTotalPrice).sum());

        return orderMapper.toDTO(orderRepository.save(order));
    }

    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDTOList(orderRepository.findAll());
    }

    public OrderDTO getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return orderMapper.toDTO(order);
    }

    @Transactional
    public OrderDTO updateOrder(UUID id, OrderDTO dto) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        existing.setStatus(dto.getStatus());
        existing.setDeliveryDate(dto.getDeliveryDate());
        existing.setRemarks(dto.getRemarks());

        return orderMapper.toDTO(orderRepository.save(existing));
    }

    public void deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}

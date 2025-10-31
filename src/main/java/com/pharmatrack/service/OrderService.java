package com.pharmatrack.service;

import com.pharmatrack.dto.OrderDTO;
import com.pharmatrack.entity.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDTO createOrder(OrderDTO dto);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(UUID id);
    OrderDTO updateOrder(UUID id, OrderDTO dto);
    void deleteOrder(UUID id);
}

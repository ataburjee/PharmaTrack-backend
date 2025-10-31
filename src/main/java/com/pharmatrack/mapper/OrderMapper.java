package com.pharmatrack.mapper;

import com.pharmatrack.dto.OrderDTO;
import com.pharmatrack.dto.OrderItemDTO;
import com.pharmatrack.entity.Order;
import com.pharmatrack.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Order ENTITY → OrderDTO
    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "supplier.name", target = "supplierName")
    OrderDTO toDTO(Order entity);

    List<OrderDTO> toDTOList(List<Order> entities);

    // OrderDTO → Order ENTITY
    @InheritInverseConfiguration
    @Mapping(target = "supplier", ignore = true) // handled in service
    Order toEntity(OrderDTO dto);

    // OrderItem ENTITY -> OrderItemDTO
    @Mapping(source = "medicine.id", target = "medicineId")
    @Mapping(source = "medicine.name", target = "medicineName")
    OrderItemDTO toOrderItemDTO(OrderItem entity);

    // OrderItemDTO → OrderItem ENTITY
    @InheritInverseConfiguration
    @Mapping(target = "medicine", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItem(OrderItemDTO dto);

    List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> list);
    List<OrderItem> toOrderItemList(List<OrderItemDTO> list);
}

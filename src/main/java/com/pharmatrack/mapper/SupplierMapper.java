package com.pharmatrack.mapper;

import com.pharmatrack.dto.SupplierRequestDTO;
import com.pharmatrack.dto.SupplierResponseDTO;
import com.pharmatrack.entity.Supplier;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {

    SupplierResponseDTO toDto(Supplier entity);

    Supplier toEntity(SupplierRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplierFromDto(SupplierRequestDTO dto, @MappingTarget Supplier entity);
}

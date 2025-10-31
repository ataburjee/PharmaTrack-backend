package com.pharmatrack.mapper;

import com.pharmatrack.dto.MedicineDTO;
import com.pharmatrack.entity.Medicine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    MedicineDTO toDTO(Medicine entity);
    Medicine toEntity(MedicineDTO dto);
}

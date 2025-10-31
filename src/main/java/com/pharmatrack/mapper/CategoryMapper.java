package com.pharmatrack.mapper;

import com.pharmatrack.dto.CategoryDTO;
import com.pharmatrack.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category entity);
    Category toEntity(CategoryDTO dto);
}
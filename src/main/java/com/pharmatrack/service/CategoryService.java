package com.pharmatrack.service;

import com.pharmatrack.dto.CategoryDTO;
import com.pharmatrack.entity.Category;
import com.pharmatrack.mapper.CategoryMapper;
import com.pharmatrack.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final CategoryMapper mapper;

    public List<CategoryDTO> getAllMedicineCategory() {

        List<CategoryDTO> categoryDTOS = categoryRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        System.out.println("categoryDTOS = " + categoryDTOS);
        return categoryDTOS;

    }

    public Long getCategoryIdByName(String categoryName) {
        if (categoryName == null) {
            throw new RuntimeException("Category is null");
        }
        return categoryRepo.findByName(categoryName.toLowerCase())
                .map(Category::getId)
                .orElseThrow(() -> new RuntimeException("Category id not found"));
    }

    public String getCategoryNameById(Long categoryId) {
        if (categoryId == null) {
            throw new RuntimeException("Category id is null");
        }
        return categoryRepo.findById(categoryId)
                .map(Category::getName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

    }

}

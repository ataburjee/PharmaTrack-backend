package com.pharmatrack.repository;

import com.pharmatrack.entity.Category;
import com.pharmatrack.enums.CategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
    Optional<Category> findByCode(CategoryCode code);
}

package com.pharmatrack.repository;

import com.pharmatrack.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
//    Optional<Medicine> findById(String id);
//    void deleteById(String id);
}

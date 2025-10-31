package com.pharmatrack.repository;

import com.pharmatrack.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    // Case-insensitive contains search for name, email or contact
    Page<Supplier> findByNameIgnoreCaseContainingOrEmailIgnoreCaseContainingOrContactContaining(
            String name, String email, String contact, Pageable pageable);

}

package com.pharmatrack.entity;

import com.pharmatrack.enums.SupplierStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "suppliers", indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 120, unique = true)
    @NotBlank
    private String email;

    @Column(length = 30)
    private String contact;

    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private SupplierStatus status = SupplierStatus.ACTIVE;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "added_by", nullable = false, updatable = false)
    private UUID addedBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by", nullable = false, updatable = false)
    private UUID updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}

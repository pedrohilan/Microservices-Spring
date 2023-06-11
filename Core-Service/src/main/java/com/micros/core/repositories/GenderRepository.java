package com.micros.core.repositories;

import com.micros.core.models.GenderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenderRepository extends JpaRepository<GenderModel, UUID> {
    boolean existsByDescription(String description);
}

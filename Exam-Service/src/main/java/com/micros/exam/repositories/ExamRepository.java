package com.micros.exam.repositories;

import com.micros.exam.models.ExamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<ExamModel, UUID> {
    boolean existsByName(String name);
}

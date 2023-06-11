package com.micros.core.repositories;

import com.micros.core.models.BaseModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, UUID> {

    List<T> findByIsDeletedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.isDeleted = true WHERE e.id = :id")
    void updateIsDeletedById(@Param("id") UUID id);
}
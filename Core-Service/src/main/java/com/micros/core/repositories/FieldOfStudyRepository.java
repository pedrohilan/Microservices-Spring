package com.micros.core.repositories;

import com.micros.core.models.FieldOfStudyModel;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOfStudyRepository extends BaseRepository<FieldOfStudyModel>{
    boolean existsByName(String name);
}

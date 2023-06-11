package com.micros.core.repositories;

import com.micros.core.models.CampusModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends BaseRepository<CampusModel>{
    boolean existsByName(String name);
}

package com.micros.core.repositories;

import com.micros.core.models.SubjectModel;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends BaseRepository<SubjectModel>{
    boolean existsByName(String name);
}

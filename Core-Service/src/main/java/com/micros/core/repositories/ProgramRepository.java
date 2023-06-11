package com.micros.core.repositories;

import com.micros.core.models.FieldOfStudyModel;
import com.micros.core.models.ProgramModel;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramRepository extends BaseRepository<ProgramModel> {
    boolean existsByName(String name);

    boolean existsByFieldOfStudy(FieldOfStudyModel fieldOfStudyModel);
}

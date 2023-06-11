package com.micros.core.repositories;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.ProgramCoordinatorModel;
import com.micros.core.models.ProgramModel;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramCoordinatorRepository extends BaseRepository<ProgramCoordinatorModel> {
    boolean existsByCoordinator(CoordinatorModel coordinator);
    boolean existsByProgram(ProgramModel program);
}

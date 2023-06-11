package com.micros.core.repositories;

import com.micros.core.models.CampusModel;
import com.micros.core.models.CampusProgramsModel;
import com.micros.core.models.ProgramModel;
import org.springframework.stereotype.Repository;


@Repository
public interface CampusProgramsRepository extends BaseRepository<CampusProgramsModel> {
    boolean existsByCampus(CampusModel campus);
    boolean existsByProgram(ProgramModel program);
}

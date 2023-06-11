package com.micros.core.repositories;

import com.micros.core.models.PersonModel;
import com.micros.core.models.ProfessorModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<ProfessorModel> {
    boolean existsByPerson(PersonModel person);
}

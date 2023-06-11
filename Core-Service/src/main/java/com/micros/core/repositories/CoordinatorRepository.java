package com.micros.core.repositories;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.PersonModel;
import org.springframework.stereotype.Repository;


@Repository
public interface CoordinatorRepository extends BaseRepository<CoordinatorModel> {
    boolean existsByPerson(PersonModel person);
}

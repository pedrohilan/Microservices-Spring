package com.micros.core.repositories;

import com.micros.core.models.PersonModel;
import com.micros.core.models.StudentModel;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends BaseRepository<StudentModel> {
    boolean existsByPerson(PersonModel person);
}

package com.micros.core.repositories;

import com.micros.core.models.PersonContactId;
import com.micros.core.models.PersonContactModel;
import com.micros.core.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContactModel, PersonContactId> {
    boolean existsById(PersonContactId id);

    boolean existsByPerson(PersonModel personModel);
}

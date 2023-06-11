package com.micros.core.repositories;

import com.micros.core.models.PersonModel;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends BaseRepository<PersonModel> {
    boolean existsByFullname(String fullname);
}

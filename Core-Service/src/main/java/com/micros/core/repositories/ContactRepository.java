package com.micros.core.repositories;

import com.micros.core.models.ContactModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends BaseRepository<ContactModel> {
    boolean existsByValue(String value);
}

package com.micros.core.repositories;

import com.micros.core.models.ContactTypeModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends BaseRepository<ContactTypeModel> {
    boolean existsByDescription(String description);
}

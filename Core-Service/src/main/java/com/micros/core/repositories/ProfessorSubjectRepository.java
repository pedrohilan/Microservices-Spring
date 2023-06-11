package com.micros.core.repositories;

import com.micros.core.models.ProfessorModel;
import com.micros.core.models.ProfessorSubjectModel;
import com.micros.core.models.SubjectModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorSubjectRepository extends BaseRepository<ProfessorSubjectModel>{

    boolean existsByProfessor(ProfessorModel professor);
    boolean existsBySubject(SubjectModel subject);
}
package com.micros.core.services;

import jakarta.transaction.Transactional;
import com.micros.core.models.ProfessorSubjectModel;
import com.micros.core.models.ProfessorModel;
import com.micros.core.models.SubjectModel;
import com.micros.core.repositories.ProfessorSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorSubjectService {

    @Autowired
    final ProfessorSubjectRepository professorSubjectRepository;

    public ProfessorSubjectService(ProfessorSubjectRepository professorSubjectRepository) {
        this.professorSubjectRepository = professorSubjectRepository;
    }

    public List<ProfessorSubjectModel> findAll(){
        return professorSubjectRepository.findByIsDeletedFalse();
    }

    public Optional<ProfessorSubjectModel> findById(UUID id) {
        return professorSubjectRepository.findById(id);
    }

    public boolean existsProfessor(ProfessorModel professor) {
        return professorSubjectRepository.existsByProfessor(professor);
    }

    public boolean existsSubject(SubjectModel subject) {
        return professorSubjectRepository.existsBySubject(subject);
    }

    @Transactional
    public Object save(ProfessorSubjectModel professorSubjectModel){
        return professorSubjectRepository.save(professorSubjectModel);
    }

    @Transactional
    public void delete(UUID id) {
        professorSubjectRepository.updateIsDeletedById(id);
    }
}

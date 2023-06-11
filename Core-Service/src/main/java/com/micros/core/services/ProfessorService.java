package com.micros.core.services;

import com.micros.core.repositories.ProfessorRepository;
import com.micros.core.models.PersonModel;
import com.micros.core.models.ProfessorModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorModel> findAll(){
        return professorRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(ProfessorModel professorModel){
        return professorRepository.save(professorModel);
    }

    public Optional<ProfessorModel> findById(UUID id) {
        return professorRepository.findById(id);
    }

    public boolean existsPerson(PersonModel person) {
        return professorRepository.existsByPerson(person);
    }

    @Transactional
    public void delete(UUID id) {
        professorRepository.updateIsDeletedById(id);
    }
}

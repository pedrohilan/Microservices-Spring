package com.micros.core.services;

import jakarta.transaction.Transactional;
import com.micros.core.models.FieldOfStudyModel;
import com.micros.core.repositories.FieldOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FieldOfStudyService {
    @Autowired
    final FieldOfStudyRepository fieldOfStudyRepository;

    public FieldOfStudyService(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    public List<FieldOfStudyModel> findAll(){
        return fieldOfStudyRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(FieldOfStudyModel fieldOfStudyModel){
        return fieldOfStudyRepository.save(fieldOfStudyModel);
    }

    public boolean existsName(String name) {
        return fieldOfStudyRepository.existsByName(name);
    }

    public Optional<FieldOfStudyModel> findById(UUID id) {
        return fieldOfStudyRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        fieldOfStudyRepository.updateIsDeletedById(id);
    }
}

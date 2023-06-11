package com.micros.core.services;

import com.micros.core.models.FieldOfStudyModel;
import com.micros.core.models.ProgramModel;
import com.micros.core.repositories.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProgramService {
    final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<ProgramModel> findAll(){
        return programRepository.findByIsDeletedFalse();
    }

    public Optional<ProgramModel> findById(UUID id) {
        return programRepository.findById(id);
    }

    public boolean existsName(String name) {
        return programRepository.existsByName(name);
    }

    public boolean existsFieldOfStudy(FieldOfStudyModel fieldOfStudyModel) {
        return programRepository.existsByFieldOfStudy(fieldOfStudyModel);
    }

    @Transactional
    public Object save(ProgramModel programModel){
        return programRepository.save(programModel);
    }

    @Transactional
    public void delete(UUID id) {
        programRepository.updateIsDeletedById(id);
    }
}

package com.micros.core.services;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.ProgramCoordinatorModel;
import com.micros.core.models.ProgramModel;
import com.micros.core.repositories.ProgramCoordinatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProgramCoordinatorService {
    final ProgramCoordinatorRepository programCoordinatorRepository;

    public ProgramCoordinatorService(ProgramCoordinatorRepository programCoordinatorRepository) {
        this.programCoordinatorRepository = programCoordinatorRepository;
    }

    public List<ProgramCoordinatorModel> findAll(){
        return programCoordinatorRepository.findByIsDeletedFalse();
    }

    public Optional<ProgramCoordinatorModel> findById(UUID id) {
        return programCoordinatorRepository.findById(id);
    }

    public boolean existsCoordinator(CoordinatorModel coordinator) {
        return programCoordinatorRepository.existsByCoordinator(coordinator);
    }

    public boolean existsProgram(ProgramModel program) {
        return programCoordinatorRepository.existsByProgram(program);
    }

    @Transactional
    public Object save(ProgramCoordinatorModel programCoordinatorModel){
        return programCoordinatorRepository.save(programCoordinatorModel);
    }

    @Transactional
    public void delete(UUID id) {
        programCoordinatorRepository.updateIsDeletedById(id);
    }
}

package com.micros.core.services;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.PersonModel;
import com.micros.core.repositories.CoordinatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoordinatorService {
    final CoordinatorRepository coordinatorRepository;

    public CoordinatorService(CoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }

    public List<CoordinatorModel> findAll(){
        return coordinatorRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(CoordinatorModel coordinatorModel){
        return coordinatorRepository.save(coordinatorModel);
    }

    public Optional<CoordinatorModel> findById(UUID id) {
        return coordinatorRepository.findById(id);
    }

    public boolean existsPerson(PersonModel person) {
        return coordinatorRepository.existsByPerson(person);
    }

    @Transactional
    public void delete(UUID id) {
        coordinatorRepository.updateIsDeletedById(id);
    }
}

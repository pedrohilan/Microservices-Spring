package com.micros.core.services;

import com.micros.core.models.CampusModel;
import com.micros.core.repositories.CampusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampusService {

    final CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    public List<CampusModel> findAll(){
        return campusRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(CampusModel campusModel){
        return campusRepository.save(campusModel);
    }

    public boolean existsName(String name) {
        return campusRepository.existsByName(name);
    }

    public Optional<CampusModel> findById(UUID id) {
        return campusRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        campusRepository.updateIsDeletedById(id);
    }
}

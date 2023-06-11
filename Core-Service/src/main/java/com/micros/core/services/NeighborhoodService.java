package com.micros.core.services;

import com.micros.core.models.NeighborhoodModel;
import com.micros.core.repositories.NeighborhoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NeighborhoodService {
    final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }
    public List<NeighborhoodModel> findAll() {return neighborhoodRepository.findByIsDeletedFalse(); }
    public Optional<NeighborhoodModel> findById(UUID id) {
        return neighborhoodRepository.findById(id);
    }

    @Transactional
    public Object save(NeighborhoodModel neighborhoodModel){
        return neighborhoodRepository.save(neighborhoodModel);
    }
}

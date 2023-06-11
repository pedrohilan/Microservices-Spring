package com.micros.core.services;

import com.micros.core.models.GenderModel;
import com.micros.core.repositories.GenderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenderService {

    final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<GenderModel> findAll(){
        return genderRepository.findAll();
    }

    @Transactional
    public Object save(GenderModel genderModel){
        return genderRepository.save(genderModel);
    }

    public boolean existsDescription(String description) {
        return genderRepository.existsByDescription(description);
    }

    public Optional<GenderModel> findById(UUID id) {
        return genderRepository.findById(id);
    }

    @Transactional
    public void delete(GenderModel genderModel) {
        genderRepository.delete(genderModel);
    }
}

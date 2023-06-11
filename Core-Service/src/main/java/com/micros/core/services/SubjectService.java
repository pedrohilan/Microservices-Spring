package com.micros.core.services;

import jakarta.transaction.Transactional;
import com.micros.core.models.SubjectModel;
import com.micros.core.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    @Autowired
    final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public List<SubjectModel> findAll(){
        return subjectRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(SubjectModel subjectModel){
        return subjectRepository.save(subjectModel);
    }

    public boolean existsName(String name) {
        return subjectRepository.existsByName(name);
    }

    public Optional<SubjectModel> findById(UUID id) {
        return subjectRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        subjectRepository.updateIsDeletedById(id);
    }
}

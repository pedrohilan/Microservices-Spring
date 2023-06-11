package com.micros.exam.services;

import com.micros.exam.repositories.ExamRepository;
import com.micros.exam.models.ExamModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {

    final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<ExamModel> findAll(){
        return examRepository.findAll();
    }

    @Transactional
    public Object save(ExamModel ExamModel){
        return examRepository.save(ExamModel);
    }

    public boolean existsName(String name) {
        return examRepository.existsByName(name);
    }

    public Optional<ExamModel> findById(UUID id) {
        return examRepository.findById(id);
    }

    @Transactional
    public void delete(ExamModel ExamModel) {
        examRepository.delete(ExamModel);
    }
}

package com.micros.core.services;

import com.micros.core.repositories.StudentRepository;
import com.micros.core.models.PersonModel;
import com.micros.core.models.StudentModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> findAll(){
        return studentRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(StudentModel studentModel){
        return studentRepository.save(studentModel);
    }

    public Optional<StudentModel> findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        studentRepository.updateIsDeletedById(id);
    }

    public boolean existsPerson(PersonModel person) {
        return studentRepository.existsByPerson(person);
    }
}

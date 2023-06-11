package com.micros.core.services;

import com.micros.core.repositories.PersonRepository;
import com.micros.core.models.PersonModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<PersonModel> findAll(){
        return personRepository.findByIsDeletedFalse();
    }

    public Optional<PersonModel> findById(UUID id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        personRepository.updateIsDeletedById(id);
    }

    @Transactional
    public Object save(PersonModel personModel){
        return personRepository.save(personModel);
    }

    public boolean existsFullname(String fullname) {
        return personRepository.existsByFullname(fullname);
    }
}

package com.micros.core.services;

import com.micros.core.repositories.PersonAddressRepository;
import com.micros.core.models.PersonAddressModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonAddressService {
    final PersonAddressRepository personAddressRepository;

    public PersonAddressService(PersonAddressRepository personAddressRepository) {
        this.personAddressRepository = personAddressRepository;
    }

    public List<PersonAddressModel> findAll(){
        return personAddressRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(PersonAddressModel personAddressModel){
        return personAddressRepository.save(personAddressModel);
    }
}

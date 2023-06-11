package com.micros.core.services;

import com.micros.core.repositories.ContactRepository;
import com.micros.core.models.ContactModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {

    final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactModel> findAll(){
        return contactRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(ContactModel contactModel){
        return contactRepository.save(contactModel);
    }

    public boolean existsValue(String value) {
        return contactRepository.existsByValue(value);
    }

    public Optional<ContactModel> findById(UUID id) {
        return contactRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        contactRepository.updateIsDeletedById(id);
    }
}

package com.micros.core.services;

import com.micros.core.models.ContactTypeModel;
import com.micros.core.repositories.ContactTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactTypeService {

    final ContactTypeRepository contactTypeRepository;

    public ContactTypeService(ContactTypeRepository contactTypeRepository) {
        this.contactTypeRepository = contactTypeRepository;
    }

    public List<ContactTypeModel> findAll(){
        return contactTypeRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(ContactTypeModel contactTypeModel){
        return contactTypeRepository.save(contactTypeModel);
    }

    public boolean existsDescription(String description) {
        return contactTypeRepository.existsByDescription(description);
    }

    public Optional<ContactTypeModel> findById(UUID id) {
        return contactTypeRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        contactTypeRepository.updateIsDeletedById(id);
    }
}

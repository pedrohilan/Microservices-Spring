package com.micros.core.services;

import com.micros.core.repositories.ContactRepository;
import com.micros.core.repositories.PersonContactRepository;
import com.micros.core.models.ContactModel;
import com.micros.core.models.PersonContactId;
import com.micros.core.models.PersonContactModel;
import com.micros.core.models.PersonModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonContactService {
    final PersonContactRepository personContactRepository;
    final ContactRepository contactRepository;

    public PersonContactService(PersonContactRepository personContactRepository, ContactRepository contactRepository) {
        this.personContactRepository = personContactRepository;
        this.contactRepository = contactRepository;
    }

    public Page<PersonContactModel> findAll(Pageable pageable){
        Page<PersonContactModel> personContacts = personContactRepository.findAll(pageable);
        for (PersonContactModel personContact : personContacts) {
            ContactModel contact = contactRepository.findById(personContact.getId().getContactModel().getId()).orElse(null);
            personContact.getId().setContactModel(contact);
        }
        return personContacts;
    }

    @Transactional
    public PersonContactModel save(PersonContactModel personContact) {
        ContactModel contact = personContact.getId().getContactModel();
        ContactModel savedContact = contactRepository.save(contact);
        personContact.getId().setContactModel(savedContact);
        return personContactRepository.save(personContact);
    }

    public boolean existsId(PersonContactId id) {
        return personContactRepository.existsById(id);
    }

    public boolean existsPerson(PersonModel personModel) {
        return personContactRepository.existsByPerson(personModel);
    }
    @Transactional
    public void delete(PersonContactModel personContactModel) {
        personContactRepository.delete(personContactModel);
    }

    public Optional<PersonContactModel> findById(PersonContactId id) {
        return personContactRepository.findById(id);
    }
}

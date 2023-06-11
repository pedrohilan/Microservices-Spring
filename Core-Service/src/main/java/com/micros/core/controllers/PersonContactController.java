package com.micros.core.controllers;

import com.micros.core.services.PersonContactService;
import com.micros.core.models.ContactModel;
import com.micros.core.models.PersonContactId;
import com.micros.core.models.PersonContactModel;
import com.micros.core.models.PersonModel;
import com.micros.core.services.ContactService;
import com.micros.core.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/personsContacts")
public class PersonContactController {

    final PersonContactService personContactService;
    final ContactService contactService;
    final PersonService personService;
    public PersonContactController(PersonContactService personContactService, ContactService contactService, PersonService personService) {
        this.personContactService = personContactService;
        this.contactService = contactService;
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<Page<PersonContactModel>> getAllPersonsContacts(@PageableDefault(page=0,size=10,sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(personContactService.findAll(pageable));
    }

    @PostMapping("/{contact_id}/{person_id}")
    public ResponseEntity<Object> savePersonContact(@PathVariable UUID contact_id, @PathVariable UUID person_id){
        try {
            Optional<ContactModel> contactModelOptional = contactService.findById(contact_id);
            Optional<PersonModel> personModelOptional = personService.findById(person_id);
            if (personModelOptional.isPresent() && contactModelOptional.isPresent()){
                var personContactId = new PersonContactId();
                personContactId.setContactModel(contactModelOptional.get());
                if(personContactService.existsId(personContactId)){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Contact is already in use!");
                }
                var personContactModel = new PersonContactModel();
                personContactModel.setPerson(personModelOptional.get());
                personContactModel.setId(personContactId);
                return ResponseEntity.status(HttpStatus.CREATED).body(personContactService.save(personContactModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person or/and Contact not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        try {
            Optional<ContactModel> contactModelOptional = contactService.findById(id);
            if (contactModelOptional.isPresent()) {
                var personContactId = new PersonContactId();
                personContactId.setContactModel(contactModelOptional.get());
                Optional<PersonContactModel> optionalPersonContact = personContactService.findById(personContactId);
                if (optionalPersonContact.isPresent()) {
                    personContactService.delete(optionalPersonContact.get());
                    return ResponseEntity.status(HttpStatus.OK).body("PersonContact deleted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PersonContact not found!");
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

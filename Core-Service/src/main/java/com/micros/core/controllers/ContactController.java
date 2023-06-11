package com.micros.core.controllers;

import com.micros.core.dtos.ContactDto;
import com.micros.core.models.ContactModel;
import com.micros.core.models.PersonContactId;
import com.micros.core.models.PersonContactModel;
import com.micros.core.services.ContactService;
import com.micros.core.services.PersonContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    final ContactService contactService;
    final PersonContactService personContactService;

    public ContactController(ContactService contactService, PersonContactService personContactService) {
        this.contactService = contactService;
        this.personContactService = personContactService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactModel>> getAllContacts(){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveContact(@RequestBody @Valid ContactDto contactDto){
        try {
            /*if(contactService.existsValue(contactDto.getValue())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Contact is already in use!");
        }*/
            ContactModel contactModel = contactDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable(value="id") UUID id){
        try {
            Optional<ContactModel> contactModelOptional = contactService.findById(id);
            if(!contactModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found!");
            }
            var personContactId = new PersonContactId();
            personContactId.setContactModel(contactModelOptional.get());
            Optional<PersonContactModel> optionalPersonContact = personContactService.findById(personContactId);
            if (optionalPersonContact.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Contact is in use at 'PersonContact'!");
            } else {
                contactService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Contact deleted successfully.");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

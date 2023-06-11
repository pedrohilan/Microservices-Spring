package com.micros.core.controllers;

import com.micros.core.dtos.PersonDto;
import com.micros.core.models.PersonModel;
import com.micros.core.services.PersonContactService;
import com.micros.core.services.PersonService;
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
@RequestMapping("/persons")
public class PersonController {
    final PersonService personService;
    final PersonContactService personContactService;

    public PersonController(PersonService personService, PersonContactService personContactService) {
        this.personService = personService;
        this.personContactService = personContactService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonModel>> getAllPersons(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePerson(@PathVariable(value="id") UUID id){
        Optional<PersonModel> personModelOptional = personService.findById(id);
        if(!personModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(personModelOptional.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PersonDto personDto){
        try {
            if(personService.existsFullname(personDto.getFullname())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Fullname is already in use!");
            }
            PersonModel personModel = personDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value="id") UUID id){
        try {
            Optional<PersonModel> personModelOptional = personService.findById(id);
            if(personModelOptional.isPresent()){
                boolean personContactModelOptional = personContactService.existsPerson(personModelOptional.get());
                if (personContactModelOptional){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Person is in use at 'PersonContact'!");
                }
                personService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

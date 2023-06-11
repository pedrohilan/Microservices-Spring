package com.micros.core.controllers;

import com.micros.core.models.AddressModel;
import com.micros.core.models.PersonAddressModel;
import com.micros.core.models.PersonModel;
import com.micros.core.services.AddressService;
import com.micros.core.services.PersonAddressService;
import com.micros.core.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/personsAddress")
public class PersonAddressController {
    final PersonAddressService personAddressService;
    final AddressService addressService;
    final PersonService personService;

    public PersonAddressController(PersonAddressService personAddressService, AddressService addressService, PersonService personService) {
        this.personAddressService = personAddressService;
        this.addressService = addressService;
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonAddressModel>> getAllPersonAddress(){
        return ResponseEntity.status(HttpStatus.OK).body(personAddressService.findAll());
    }

    @PostMapping("/{address_id}/{person_id}")
    public ResponseEntity<Object> savePersonAddress(@PathVariable UUID address_id, @PathVariable UUID person_id){
        try {
            Optional<AddressModel> addressModelOptional = addressService.findById(address_id);
            Optional<PersonModel> personModelOptional = personService.findById(person_id);
            if (personModelOptional.isPresent() && addressModelOptional.isPresent()){
                var personAddressModel = new PersonAddressModel();
                personAddressModel.setPerson(personModelOptional.get());
                personAddressModel.setAddress(addressModelOptional.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(personAddressService.save(personAddressModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person or/and Address not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

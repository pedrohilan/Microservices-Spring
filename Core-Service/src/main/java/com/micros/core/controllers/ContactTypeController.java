package com.micros.core.controllers;

import com.micros.core.dtos.ContactTypeDto;
import com.micros.core.models.ContactTypeModel;
import com.micros.core.services.ContactTypeService;
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
@RequestMapping("/contactType")
public class ContactTypeController {

    final ContactTypeService contactTypeService;

    public ContactTypeController(ContactTypeService contactTypeService) {
        this.contactTypeService = contactTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactTypeModel>> getAllContactsTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(contactTypeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveContactType(@RequestBody @Valid ContactTypeDto contactTypeDto){
        try {
            if(contactTypeService.existsDescription(contactTypeDto.getDescription())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Description is already in use!");
            }
            ContactTypeModel contactTypeModel = contactTypeDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(contactTypeService.save(contactTypeModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContactType(@PathVariable(value="id") UUID id){
        try {
            Optional<ContactTypeModel> contactTypeModelOptional = contactTypeService.findById(id);
            if(!contactTypeModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact Type not found!");
            }
            contactTypeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Contact Type deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

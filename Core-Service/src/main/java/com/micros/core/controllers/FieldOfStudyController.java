package com.micros.core.controllers;

import jakarta.validation.Valid;
import com.micros.core.dtos.FieldOfStudyDto;
import com.micros.core.models.FieldOfStudyModel;
import com.micros.core.services.FieldOfStudyService;
import com.micros.core.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/fieldOfStudy")
public class FieldOfStudyController {

    @Autowired
    final FieldOfStudyService fieldOfStudyService;

    @Autowired
    final ProgramService programService;

    public FieldOfStudyController(FieldOfStudyService fieldOfStudyService, ProgramService programService) {
        this.fieldOfStudyService = fieldOfStudyService;
        this.programService = programService;
    }

    @GetMapping()
    public ResponseEntity<List<FieldOfStudyModel>> getAllFieldOfStudy(){
        return ResponseEntity.status(HttpStatus.OK).body(fieldOfStudyService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveFieldOfStudy(@RequestBody @Valid FieldOfStudyDto fieldOfStudyDto){
        try {
            if(fieldOfStudyService.existsName(fieldOfStudyDto.getName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            FieldOfStudyModel fieldOfStudyModel = fieldOfStudyDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldOfStudyService.save(fieldOfStudyModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFieldOfStudy(@PathVariable(value="id") UUID id){
        try{
            Optional<FieldOfStudyModel> fieldOfStudyModelOptional = fieldOfStudyService.findById(id);
            if(!fieldOfStudyModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FieldOfStudy not found!");
            }
            if(programService.existsFieldOfStudy(fieldOfStudyModelOptional.get())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("FieldOfStudy is already in use - Program!");
            }
            fieldOfStudyService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("FieldOfStudy deleted successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFieldOfStudy(@PathVariable(value="id") UUID id, @RequestBody @Valid FieldOfStudyDto fieldOfStudyDto){
        try{
            Optional<FieldOfStudyModel> fieldOfStudyModelOptional = fieldOfStudyService.findById(id);
            if(!fieldOfStudyModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FieldOfStudy not found!");
            }
            if (!fieldOfStudyDto.getName().equals(fieldOfStudyModelOptional.get().getName())) {
                if(fieldOfStudyService.existsName(fieldOfStudyDto.getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
            FieldOfStudyModel fieldOfStudyModel = fieldOfStudyDto.toModel();
            fieldOfStudyModel.setId(fieldOfStudyModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(fieldOfStudyService.save(fieldOfStudyModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

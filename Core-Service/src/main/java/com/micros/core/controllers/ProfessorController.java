package com.micros.core.controllers;

import com.micros.core.dtos.ProfessorDto;
import com.micros.core.models.ProfessorModel;
import com.micros.core.services.ProfessorService;
import jakarta.validation.Valid;
import com.micros.core.services.ProfessorSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    final ProfessorService professorService;
    @Autowired
    final ProfessorSubjectService professorSubjectService;

    public ProfessorController(ProfessorService professorService, ProfessorSubjectService professorSubjectService) {
        this.professorService = professorService;
        this.professorSubjectService = professorSubjectService;
    }

    @GetMapping()
    public ResponseEntity<List<ProfessorModel>> getAllProfessors(){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveProfessor(@RequestBody @Valid ProfessorDto professorDto){
        try {
            if(professorService.existsPerson(professorDto.getPerson())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Person is already in use!");
            }
            ProfessorModel professorModel = professorDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable(value="id") UUID id){
        try {
            Optional<ProfessorModel> professorModelOptional = professorService.findById(id);
            if(!professorModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found!");
            }
            if(professorSubjectService.existsProfessor(professorModelOptional.get())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Professor is already in use - ProfessorSubject!");
            }
            professorService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Professor deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

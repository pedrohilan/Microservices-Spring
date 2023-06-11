package com.micros.core.controllers;

import com.micros.core.dtos.ProgramDto;
import com.micros.core.models.ProgramModel;
import com.micros.core.services.CampusProgramsService;
import com.micros.core.services.ProgramCoordinatorService;
import com.micros.core.services.ProgramService;
import jakarta.validation.Valid;
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
@RequestMapping("/programs")
public class ProgramController {

    final ProgramService programService;
    final ProgramCoordinatorService programCoordinatorService;
    final CampusProgramsService campusProgramsService;

    public ProgramController(ProgramService programService, ProgramCoordinatorService programCoordinatorService, CampusProgramsService campusProgramsService) {
        this.programService = programService;
        this.programCoordinatorService = programCoordinatorService;
        this.campusProgramsService = campusProgramsService;
    }

    @GetMapping()
    public ResponseEntity<List<ProgramModel>> getAllPrograms(){
        return ResponseEntity.status(HttpStatus.OK).body(programService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveProgram(@RequestBody @Valid ProgramDto programDto){
        try {
            if(programService.existsName(programDto.getName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            ProgramModel programModel = programDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(programService.save(programModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProgram(@PathVariable(value="id") UUID id){
        try {
            Optional<ProgramModel> programModelOptional = programService.findById(id);
            if (!programModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Program not found!");
            }
            if (programCoordinatorService.existsProgram(programModelOptional.get())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Program is already in use - ProgramCoordinator!");
            }
            if (campusProgramsService.existsProgram(programModelOptional.get())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Program is already in use - CampusProgram!");
            }
            programService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Program deleted successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProgram(@PathVariable(value="id") UUID id, @RequestBody @Valid ProgramDto programDto){
        try {
            Optional<ProgramModel> programModelOptional = programService.findById(id);
            if(!programModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Program not found!");
            }
            if (!programDto.getName().equals(programModelOptional.get().getName())) {
                if(programService.existsName(programDto.getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
            ProgramModel programModel = programDto.toModel();
            programModel.setId(programModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(programService.save(programModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

package com.micros.core.controllers;

import com.micros.core.models.CampusModel;
import com.micros.core.models.CampusProgramsModel;
import com.micros.core.models.ProgramModel;
import lit.unichristus.edu.br.core.models.*;
import com.micros.core.services.CampusProgramsService;
import com.micros.core.services.CampusService;
import com.micros.core.services.ProgramService;
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
@RequestMapping("/campusPrograms")
public class CampusProgramsController {
    final CampusProgramsService campusProgramsService;
    final CampusService campusService;
    final ProgramService programService;

    public CampusProgramsController(CampusProgramsService campusProgramsService, CampusService campusService, ProgramService programService) {
        this.campusProgramsService = campusProgramsService;
        this.campusService = campusService;
        this.programService = programService;
    }

    @GetMapping()
    public ResponseEntity<List<CampusProgramsModel>> getAllCampusPrograms(){
        return ResponseEntity.status(HttpStatus.OK).body(campusProgramsService.findAll());
    }

    @PostMapping("/{campus_id}/{program_id}")
    public ResponseEntity<Object> saveCampusPrograms(@PathVariable UUID campus_id, @PathVariable UUID program_id){
        try {
            Optional<CampusModel> campusModelOptional = campusService.findById(campus_id);
            Optional<ProgramModel> programModelOptional = programService.findById(program_id);
            if (campusModelOptional.isPresent() && programModelOptional.isPresent()){
                var campusProgramsModel = new CampusProgramsModel();
                campusProgramsModel.setCampus(campusModelOptional.get());
                campusProgramsModel.setProgram(programModelOptional.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(campusProgramsService.save(campusProgramsModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campus or/and Program not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

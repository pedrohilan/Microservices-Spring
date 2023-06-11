package com.micros.core.controllers;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.ProgramCoordinatorModel;
import com.micros.core.models.ProgramModel;
import com.micros.core.services.CoordinatorService;
import com.micros.core.services.ProgramCoordinatorService;
import com.micros.core.services.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/programCoordinator")
public class ProgramCoordinatorController {
    final ProgramCoordinatorService programCoordinatorService;
    final ProgramService programService;
    final CoordinatorService coordinatorService;

    public ProgramCoordinatorController(ProgramCoordinatorService programCoordinatorService, ProgramService programService, CoordinatorService coordinatorService) {
        this.programCoordinatorService = programCoordinatorService;
        this.programService = programService;
        this.coordinatorService = coordinatorService;
    }

    @GetMapping()
    public ResponseEntity<List<ProgramCoordinatorModel>> getAllProgramCoordinators(){
        return ResponseEntity.status(HttpStatus.OK).body(programCoordinatorService.findAll());
    }

    @PostMapping("/{program_id}/{coordinator_id}")
    public ResponseEntity<Object> saveProgramCoordinator(@PathVariable UUID program_id, @PathVariable UUID coordinator_id){
        try {
            Optional<ProgramModel> programModelOptional = programService.findById(program_id);
            Optional<CoordinatorModel> coordinatorModelOptional = coordinatorService.findById(coordinator_id);
            if (programModelOptional.isPresent() && coordinatorModelOptional.isPresent()){
                var programCoordinatorModel = new ProgramCoordinatorModel();
                programCoordinatorModel.setProgram(programModelOptional.get());
                programCoordinatorModel.setCoordinator(coordinatorModelOptional.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(programCoordinatorService.save(programCoordinatorModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Program or/and Coordinator not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProgramCoordinator(@PathVariable(value="id") UUID id){
        try {
            Optional<ProgramCoordinatorModel> programCoordinatorModel = programCoordinatorService.findById(id);
            if(!programCoordinatorModel.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProgramCoordinator not found!");
            }
            programCoordinatorService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("ProgramCoordinator deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

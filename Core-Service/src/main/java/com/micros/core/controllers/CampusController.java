package com.micros.core.controllers;

import com.micros.core.dtos.CampusDto;
import com.micros.core.models.CampusModel;
import com.micros.core.services.CampusProgramsService;
import com.micros.core.services.CampusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/campus")
public class CampusController {

    final CampusService campusService;
    final CampusProgramsService campusProgramsService;

    public CampusController(CampusService campusService, CampusProgramsService campusProgramsService) {
        this.campusService = campusService;
        this.campusProgramsService = campusProgramsService;
    }

    @GetMapping()
    public ResponseEntity<List<CampusModel>> getAllCampus(){
        return ResponseEntity.status(HttpStatus.OK).body(campusService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveCampus(@RequestBody @Valid CampusDto campusDto){
        try {
            if(campusService.existsName(campusDto.getName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            }
            CampusModel campusModel = campusDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(campusService.save(campusModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCampus(@PathVariable(value="id") UUID id){
        try{
            Optional<CampusModel> campusModelOptional = campusService.findById(id);
            if(!campusModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campus not found!");
            }
            if(campusProgramsService.existsCampus(campusModelOptional.get())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Campus is already in use - CampusProgram!");
            }
            campusService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Campus deleted successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCampus(@PathVariable(value="id") UUID id, @RequestBody @Valid CampusDto campusDto){
        try{
            Optional<CampusModel> campusModelOptional = campusService.findById(id);
            if(!campusModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campus not found!");
            }
            if (!campusDto.getName().equals(campusModelOptional.get().getName())) {
                if(campusService.existsName(campusDto.getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
            CampusModel campusModel = campusDto.toModel();
            campusModel.setId(campusModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(campusService.save(campusModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

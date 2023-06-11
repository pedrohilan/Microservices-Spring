package com.micros.core.controllers;

import com.micros.core.dtos.GenderDto;
import com.micros.core.models.GenderModel;
import com.micros.core.services.GenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/genders")
public class GenderController {

    final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping()
    public ResponseEntity<List<GenderModel>> getAllGenders(){
        return ResponseEntity.status(HttpStatus.OK).body(genderService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveGender(@RequestBody @Valid GenderDto genderDto){
        try {
            if(genderService.existsDescription(genderDto.getDescription())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Description is already in use!");
            }
            GenderModel genderModel = genderDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(genderService.save(genderModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGender(@PathVariable(value="id") UUID id){
        try {
            Optional<GenderModel> genderModelOptional = genderService.findById(id);
            if(!genderModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found!");
            }
            genderService.delete(genderModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Gender deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGender(@PathVariable(value="id") UUID id, @RequestBody @Valid GenderDto genderDto){
        try {
            Optional<GenderModel> genderModelOptional = genderService.findById(id);
            if(!genderModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found!");
            }
            if (!genderDto.getDescription().equals(genderModelOptional.get().getDescription())) {
                if(genderService.existsDescription(genderDto.getDescription())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Description is already in use!");
                }
            }
            GenderModel genderModel = genderDto.toModel();
            genderModel.setId(genderModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(genderService.save(genderModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

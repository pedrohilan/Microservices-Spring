package com.micros.core.controllers;

import com.micros.core.dtos.NeighborhoodDto;
import com.micros.core.models.NeighborhoodModel;
import com.micros.core.services.NeighborhoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/neighborhood")
public class NeighborhoodController {
    final NeighborhoodService neighborhoodService;

    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneNeighborhood(@PathVariable(value="id") UUID id){
        Optional<NeighborhoodModel> neighborhoodModelOptional = neighborhoodService.findById(id);
        if(!neighborhoodModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Neighborhood not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(neighborhoodModelOptional.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> saveNeighborhood(@RequestBody @Valid NeighborhoodDto neighborhoodDto){
        try {
            NeighborhoodModel neighborhoodModel = neighborhoodDto.toModel();
            return ResponseEntity.status(HttpStatus.CREATED).body(neighborhoodService.save(neighborhoodModel));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

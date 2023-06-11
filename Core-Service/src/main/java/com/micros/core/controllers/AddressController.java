package com.micros.core.controllers;

import com.micros.core.dtos.AddressDto;
import com.micros.core.models.AddressModel;
import com.micros.core.models.NeighborhoodModel;
import com.micros.core.services.AddressService;
import com.micros.core.services.NeighborhoodService;
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
@RequestMapping("/address")
public class AddressController {
    final AddressService addressService;
    final NeighborhoodService neighborhoodService;

    public AddressController(AddressService addressService, NeighborhoodService neighborhoodService) {
        this.addressService = addressService;
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping()
    public ResponseEntity<List<AddressModel>> getAllAddress(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> saveAddress(@RequestBody @Valid AddressDto addressDto){
        try {
            Optional<NeighborhoodModel> neighborhoodModelOptional = neighborhoodService.findById(addressDto.getNeighborhoodModel().getId());
            if (neighborhoodModelOptional.isPresent()){
                AddressModel addressModel = addressDto.toModel();
                addressModel.setNeighborhood(neighborhoodModelOptional.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(addressModel));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Neighborhood not found!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable(value="id") UUID id){
        try {
            Optional<AddressModel> addressModelOptional = addressService.findById(id);
            if(!addressModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found!");
            }
            addressService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Address deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

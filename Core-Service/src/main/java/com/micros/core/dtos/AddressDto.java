package com.micros.core.dtos;

import com.micros.core.models.AddressModel;
import com.micros.core.models.NeighborhoodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressDto {

    @NotBlank(message = "StreetName may not be blank")
    @Size(max=120)
    private String streetName;

    @NotBlank(message = "Number may not be blank")
    @Size(max=10)
    private String number;

    @NotBlank(message = "AdditionalAddress may not be blank")
    @Size(max=60)
    private String additionalAddress;

    @NotNull(message = "Neighborhood may not be null")
    private NeighborhoodModel neighborhoodModel;

    @NotBlank(message = "Zip may not be blank")
    @Size(max=8)
    private String zip;

    public AddressModel toModel(){
        return new AddressModel(streetName, number, additionalAddress, neighborhoodModel, zip);
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public NeighborhoodModel getNeighborhoodModel() {
        return neighborhoodModel;
    }

    public void setNeighborhoodModel(NeighborhoodModel neighborhoodModel) {
        this.neighborhoodModel = neighborhoodModel;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

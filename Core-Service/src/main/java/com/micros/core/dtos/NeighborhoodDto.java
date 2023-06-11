package com.micros.core.dtos;

import com.micros.core.models.CityModel;
import com.micros.core.models.NeighborhoodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NeighborhoodDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max = 30)
    private String name;

    @NotNull(message = "City may not be null")
    private CityModel city;

    public NeighborhoodModel toModel(){
        return new NeighborhoodModel(name, city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }
}

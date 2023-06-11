package com.micros.core.dtos;

import com.micros.core.models.CampusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CampusDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max=30)
    private String name;

    public CampusModel toModel(){
        return new CampusModel(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

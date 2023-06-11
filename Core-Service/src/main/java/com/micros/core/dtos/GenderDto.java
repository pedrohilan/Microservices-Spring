package com.micros.core.dtos;

import com.micros.core.models.GenderModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GenderDto {

    @NotBlank(message = "Description may not be blank")
    @Size(max=20)
    private String description;

    public GenderModel toModel(){
        return new GenderModel(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

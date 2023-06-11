package com.micros.core.dtos;

import com.micros.core.models.FieldOfStudyModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FieldOfStudyDto {
    @NotBlank(message = "Name may not be blank")
    @Size(max=20)
    private String name;

    public FieldOfStudyModel toModel(){
        return new FieldOfStudyModel(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.micros.core.dtos;

import com.micros.core.models.SubjectModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SubjectDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max=30)
    private String name;

    public SubjectModel toModel(){
        return new SubjectModel(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

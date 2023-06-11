package com.micros.exam.dtos;

import com.micros.exam.models.ExamModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ExamDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max=30)
    private String name;

    public ExamModel toModel(){
        return new ExamModel(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

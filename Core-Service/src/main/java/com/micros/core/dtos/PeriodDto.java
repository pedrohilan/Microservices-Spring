package com.micros.core.dtos;

import com.micros.core.models.PeriodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PeriodDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max=5)
    private char name;

    public PeriodModel toModel(){
        return new PeriodModel(name);
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
}

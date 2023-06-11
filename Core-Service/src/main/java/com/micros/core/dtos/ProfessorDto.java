package com.micros.core.dtos;

import com.micros.core.models.PersonModel;
import com.micros.core.models.ProfessorModel;
import jakarta.validation.constraints.NotNull;

public class ProfessorDto {
    @NotNull(message = "Person may not be null")
    private PersonModel person;

    public ProfessorModel toModel(){
        return new ProfessorModel(person);
    }

    public PersonModel getPerson() {
        return person;
    }
    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

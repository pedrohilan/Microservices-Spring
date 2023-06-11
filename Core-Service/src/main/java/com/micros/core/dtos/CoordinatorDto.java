package com.micros.core.dtos;

import com.micros.core.models.CoordinatorModel;
import com.micros.core.models.PersonModel;
import jakarta.validation.constraints.NotNull;

public class CoordinatorDto {
    @NotNull(message = "Person may not be null")
    private PersonModel person;

    public CoordinatorModel toModel(){
        return new CoordinatorModel(person);
    }

    public PersonModel getPerson() {
        return person;
    }
    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

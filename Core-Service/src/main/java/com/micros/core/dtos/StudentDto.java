package com.micros.core.dtos;

import com.micros.core.models.PersonModel;
import com.micros.core.models.StudentModel;
import jakarta.validation.constraints.NotNull;

public class StudentDto {
    @NotNull(message = "Person may not be null")
    private PersonModel person;
    public PersonModel getPerson() {
        return person;
    }
    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public StudentModel toModel(){
        return new StudentModel(person);
    }
}

package com.micros.core.dtos;

import com.micros.core.models.ContactModel;
import com.micros.core.models.PersonModel;
import jakarta.validation.constraints.NotNull;

public class PersonContactDto {

    @NotNull(message = "Contact may not be null")
    private ContactModel id;

    @NotNull(message = "Person may not be null")
    private PersonModel person;

    public ContactModel getId() {
        return id;
    }

    public void setId(ContactModel id) {
        this.id = id;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

package com.micros.core.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "person_contact")
public class PersonContactModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PersonContactId id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel person;

    public PersonContactId getId() {
        return id;
    }
    public void setId(PersonContactId id) {
        this.id = id;
    }

    public PersonModel getPerson() {
        return person;
    }
    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

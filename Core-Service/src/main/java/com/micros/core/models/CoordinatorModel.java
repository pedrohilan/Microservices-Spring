package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "coordinator")
public class CoordinatorModel extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel person;

    public CoordinatorModel() {
    }

    public CoordinatorModel(PersonModel person) {
        this.person = person;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

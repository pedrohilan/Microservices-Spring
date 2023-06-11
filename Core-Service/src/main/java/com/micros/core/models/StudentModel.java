package com.micros.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class StudentModel extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel person;

    public StudentModel() {
    }

    public StudentModel(PersonModel person) {
        this.person = person;
    }


    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}

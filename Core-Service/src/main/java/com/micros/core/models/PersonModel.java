package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.micros.core.enums.GenderEnum;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "person")
public class PersonModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 200)
    private String fullname;

    @OneToOne(mappedBy = "person")
    private ProfessorModel professor;

    @OneToOne(mappedBy = "person")
    private StudentModel student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

    public PersonModel() {
    }

    public PersonModel(String fullname, GenderEnum gender) {
        this.fullname = fullname;
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}

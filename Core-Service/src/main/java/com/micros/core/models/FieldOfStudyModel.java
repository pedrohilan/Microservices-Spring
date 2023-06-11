package com.micros.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "field_of_study")
public class FieldOfStudyModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 20)
    private String name;
    public FieldOfStudyModel() {
    }

    public FieldOfStudyModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

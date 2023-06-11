package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "campus")
public class CampusModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 30)
    private String name;

    public CampusModel() {
    }

    public CampusModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.micros.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "period")
public class PeriodModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 5)
    private char name;

    public PeriodModel() {
    }

    public PeriodModel(char name) {
        this.name = name;
    }


    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
}

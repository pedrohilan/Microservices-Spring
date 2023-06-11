package com.micros.exam.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "exam")
public class ExamModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 30)
    private String name;

    public ExamModel() {
    }

    public ExamModel(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

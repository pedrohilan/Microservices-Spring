package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "class_students")
public class ClassStudentsModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "class_id")
    private ClassModel classModel;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "student_id")
    private StudentModel student;

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }
}

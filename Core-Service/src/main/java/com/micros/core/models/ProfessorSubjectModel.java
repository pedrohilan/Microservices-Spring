package com.micros.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "professor_subject")
public class ProfessorSubjectModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "subject_id")
    private SubjectModel subject;

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }
}

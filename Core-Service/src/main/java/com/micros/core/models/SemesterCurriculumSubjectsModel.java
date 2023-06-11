package com.micros.core.models;

import com.micros.core.enums.TypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "semester_curriculum_subjects")
public class SemesterCurriculumSubjectsModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "semester_curriculum_id")
    private SemesterCurriculumModel semesterCurriculum;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "subject_id")
    private SubjectModel subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEnum type;

    @Column(nullable = false)
    private short creditHours;

    @Column(nullable = false, length = 10)
    private String lyceumCode;

    public SemesterCurriculumModel getSemesterCurriculum() {
        return semesterCurriculum;
    }

    public void setSemesterCurriculum(SemesterCurriculumModel semesterCurriculum) {
        this.semesterCurriculum = semesterCurriculum;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public short getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(short creditHours) {
        this.creditHours = creditHours;
    }

    public String getLyceumCode() {
        return lyceumCode;
    }

    public void setLyceumCode(String lyceumCode) {
        this.lyceumCode = lyceumCode;
    }
}


package com.micros.core.models;

import com.micros.core.enums.SemesterEnum;
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
@Table(name = "semester_curriculum")
public class SemesterCurriculumModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "curriculum_id")
    private CurriculumModel curriculum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SemesterEnum semester;

    public CurriculumModel getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(CurriculumModel curriculum) {
        this.curriculum = curriculum;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }
}

package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "class")
public class ClassModel extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "semester_curriculum_subjects_id")
    private SemesterCurriculumSubjectsModel semesterCurriculumSubjects;

    public ClassModel() {
    }

    public ClassModel(SemesterCurriculumSubjectsModel semesterCurriculumSubjects) {
        this.semesterCurriculumSubjects = semesterCurriculumSubjects;
    }

    public SemesterCurriculumSubjectsModel getSemesterCurriculumSubjects() {
        return semesterCurriculumSubjects;
    }

    public void setSemesterCurriculumSubjects(SemesterCurriculumSubjectsModel semesterCurriculumSubjects) {
        this.semesterCurriculumSubjects = semesterCurriculumSubjects;
    }
}

package com.micros.core.models;

import jakarta.persistence.*;
import com.micros.core.enums.CourseFormatEnum;
import com.micros.core.enums.DegreeEnum;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "program")
public class ProgramModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "field_of_study_id")
    private FieldOfStudyModel fieldOfStudy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DegreeEnum degree;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseFormatEnum courseFormat;

    public ProgramModel() {
    }

    public ProgramModel(String name, FieldOfStudyModel fieldOfStudy, DegreeEnum degree, CourseFormatEnum courseFormat) {
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
        this.degree = degree;
        this.courseFormat = courseFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldOfStudyModel getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudyModel fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public DegreeEnum getDegree() {
        return degree;
    }

    public void setDegree(DegreeEnum degree) {
        this.degree = degree;
    }

    public CourseFormatEnum getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(CourseFormatEnum courseFormat) {
        this.courseFormat = courseFormat;
    }
}

package com.micros.core.dtos;

import com.micros.core.enums.CourseFormatEnum;
import com.micros.core.enums.DegreeEnum;
import com.micros.core.models.FieldOfStudyModel;
import com.micros.core.models.ProgramModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProgramDto {

    @NotBlank(message = "Name may not be blank")
    @Size(max=30)
    private String name;

    @NotNull(message = "FieldOfStudy may not be null")
    private FieldOfStudyModel fieldOfStudy;

    @NotNull(message = "Degree may not be null")
    private DegreeEnum degree;

    @NotNull(message = "CourseFormat may not be null")
    private CourseFormatEnum courseFormat;

    public ProgramModel toModel(){
        return new ProgramModel(name, fieldOfStudy, degree, courseFormat);
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

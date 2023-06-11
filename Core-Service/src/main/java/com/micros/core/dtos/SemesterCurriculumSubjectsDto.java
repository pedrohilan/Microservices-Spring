package com.micros.core.dtos;

import com.micros.core.enums.TypeEnum;
import com.micros.core.models.SemesterCurriculumModel;
import com.micros.core.models.SubjectModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SemesterCurriculumSubjectsDto {

    @NotNull(message = "SemesterCurriculum may not be null")
    private SemesterCurriculumModel semesterCurriculum;

    @NotNull(message = "Subject may not be null")
    private SubjectModel subject;

    @NotNull(message = "Type may not be null")
    private TypeEnum type;

    @NotBlank(message = "CreditHours may not be blank")
    private short creditHours;

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
}

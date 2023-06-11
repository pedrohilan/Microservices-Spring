package com.micros.core.dtos;

import com.micros.core.enums.SemesterEnum;
import com.micros.core.models.CurriculumModel;
import jakarta.validation.constraints.NotNull;

public class SemesterCurriculumDto {

    @NotNull(message = "Curriculum may not be null")
    private CurriculumModel curriculum;

    @NotNull(message = "Semester may not be null")
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

package com.micros.core.dtos;

import com.micros.core.models.ClassModel;
import com.micros.core.models.SemesterCurriculumSubjectsModel;
import jakarta.validation.constraints.NotNull;

public class ClassDto {
    @NotNull(message = "SemesterCurriculumSubjects may not be null")
    private SemesterCurriculumSubjectsModel semesterCurriculumSubjects;

    public ClassModel toModel(){
        return new ClassModel(semesterCurriculumSubjects);
    }

    public SemesterCurriculumSubjectsModel getSemesterCurriculumSubjects() {
        return semesterCurriculumSubjects;
    }

    public void setSemesterCurriculumSubjects(SemesterCurriculumSubjectsModel semesterCurriculumSubjects) {
        this.semesterCurriculumSubjects = semesterCurriculumSubjects;
    }
}

package com.micros.core.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "curriculum")
public class CurriculumModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "program_id")
    private ProgramModel program;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "period_id")
    private PeriodModel period;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "class_schedule_id")
    private ClassSchedulesModel classSchedule;

    public ProgramModel getProgram() {
        return program;
    }

    public void setProgram(ProgramModel program) {
        this.program = program;
    }

    public PeriodModel getPeriod() {
        return period;
    }

    public void setPeriod(PeriodModel period) {
        this.period = period;
    }

    public ClassSchedulesModel getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedulesModel classSchedule) {
        this.classSchedule = classSchedule;
    }
}

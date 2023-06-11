package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.micros.core.enums.ClassPeriodEnum;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "class_schedules")
public class ClassSchedulesModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Time initialTime;

    @Column(nullable = false)
    private Time finalTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassPeriodEnum classPeriod;

    public ClassSchedulesModel() {
    }

    public ClassSchedulesModel(Time initialTime, Time finalTime, ClassPeriodEnum classPeriod) {
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.classPeriod = classPeriod;
    }

    public Time getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Time initialTime) {
        this.initialTime = initialTime;
    }

    public Time getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Time finalTime) {
        this.finalTime = finalTime;
    }

    public ClassPeriodEnum getClassPeriod() {
        return classPeriod;
    }

    public void setClassPeriod(ClassPeriodEnum classPeriod) {
        this.classPeriod = classPeriod;
    }
}

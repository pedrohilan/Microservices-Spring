package com.micros.core.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "campus_programs")
public class CampusProgramsModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "campus_id")
    private CampusModel campus;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "program_id")
    private ProgramModel program;

    public ProgramModel getProgram() {
        return program;
    }

    public void setProgram(ProgramModel program) {
        this.program = program;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }
}

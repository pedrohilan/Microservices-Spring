package com.micros.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "program_coordinator")
public class ProgramCoordinatorModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "program_id")
    private ProgramModel program;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "coordinator_id")
    private CoordinatorModel coordinator;


    public ProgramModel getProgram() {
        return program;
    }

    public void setProgram(ProgramModel program) {
        this.program = program;
    }

    public CoordinatorModel getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorModel coordinator) {
        this.coordinator = coordinator;
    }
}

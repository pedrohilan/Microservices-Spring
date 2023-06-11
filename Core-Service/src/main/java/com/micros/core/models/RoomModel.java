package com.micros.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "room")
public class RoomModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "campus_id")
    private CampusModel campus;
    @Column(nullable = false, length = 10)
    private String number;

    public RoomModel() {
    }

    public RoomModel(CampusModel campus, String number) {
        this.campus = campus;
        this.number = number;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

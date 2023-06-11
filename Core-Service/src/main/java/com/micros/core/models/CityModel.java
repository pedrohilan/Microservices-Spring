package com.micros.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "city")
public class CityModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "state_id")
    private StateModel state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }
}

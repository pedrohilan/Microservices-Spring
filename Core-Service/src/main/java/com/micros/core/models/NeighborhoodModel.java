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
@Table(name = "neighborhood")
public class NeighborhoodModel extends BaseModel implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;


    @Column(nullable = false, length = 30)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "city_id")
    private CityModel city;

    public NeighborhoodModel() {
    }

    public NeighborhoodModel(String name, CityModel city) {
        this.name = name;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }
}

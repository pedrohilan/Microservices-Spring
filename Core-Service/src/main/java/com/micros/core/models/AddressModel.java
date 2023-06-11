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
@Table(name = "address")
public class AddressModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 120)
    private String streetName;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 60)
    private String additionalAddress;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "neighborhood_id")
    private NeighborhoodModel neighborhood;

    @Column(nullable = false, length = 8)
    private String zip;

    public AddressModel() {
    }

    public AddressModel(String streetName, String number, String additionalAddress, NeighborhoodModel neighborhood, String zip) {
        this.streetName = streetName;
        this.number = number;
        this.additionalAddress = additionalAddress;
        this.neighborhood = neighborhood;
        this.zip = zip;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public NeighborhoodModel getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(NeighborhoodModel neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

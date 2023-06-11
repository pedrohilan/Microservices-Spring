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
@Table(name = "contact")
public class ContactModel extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "contactType_id")
    private ContactTypeModel contactType ;

    public ContactModel() {
    }

    public ContactModel(String value, ContactTypeModel contactType) {
        this.value = value;
        this.contactType = contactType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactTypeModel getContactType() {
        return contactType;
    }

    public void setContactType(ContactTypeModel contactType) {
        this.contactType = contactType;
    }
}

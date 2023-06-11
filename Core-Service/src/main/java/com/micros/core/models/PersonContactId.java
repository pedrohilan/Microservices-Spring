package com.micros.core.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class PersonContactId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "contact_id")
    private ContactModel contactModel;

    public ContactModel getContactModel() {
        return contactModel;
    }
    public void setContactModel(ContactModel contactModel) {
        this.contactModel = contactModel;
    }
}

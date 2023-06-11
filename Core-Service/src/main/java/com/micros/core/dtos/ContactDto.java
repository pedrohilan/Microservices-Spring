package com.micros.core.dtos;

import com.micros.core.models.ContactModel;
import com.micros.core.models.ContactTypeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactDto {

    @NotBlank(message = "Value may not be blank")
    @Size(max=60)
    private String value;
    @NotNull(message = "Contact Type may not be null")
    private ContactTypeModel contactType;

    public ContactModel toModel(){
        return new ContactModel(value, contactType);
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

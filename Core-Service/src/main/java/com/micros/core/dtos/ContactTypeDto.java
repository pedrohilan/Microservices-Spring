package com.micros.core.dtos;

import com.micros.core.models.ContactTypeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactTypeDto {

    @NotBlank(message = "Description may not be blank")
    @Size(max=20)
    private String description;

    public ContactTypeModel toModel(){
        return new ContactTypeModel(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

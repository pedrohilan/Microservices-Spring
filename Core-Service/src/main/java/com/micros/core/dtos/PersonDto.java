package com.micros.core.dtos;

import com.micros.core.enums.GenderEnum;
import com.micros.core.models.PersonModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonDto {

    @NotBlank(message = "Fullname may not be blank")
    @Size(max=200)
    private String fullname;

    @NotNull(message = "Gender may not be null")
    private GenderEnum gender;

    public PersonModel toModel(){
        return new PersonModel(fullname, gender);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}

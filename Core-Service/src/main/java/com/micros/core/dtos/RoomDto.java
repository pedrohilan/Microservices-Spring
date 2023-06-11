package com.micros.core.dtos;

import com.micros.core.models.CampusModel;
import com.micros.core.models.RoomModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoomDto {

    @NotNull(message = "Campus may not be null")
    private CampusModel campus;
    
    @NotBlank(message = "Number may not be blank")
    @Size(max=10)
    private String number;

    public RoomModel toModel(){
        return new RoomModel(campus, number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }
}

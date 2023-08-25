package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleSave {

    @JsonProperty("person")
    private  PersonGetId perso;

    @JsonProperty("role")
    private  PersonGetId role;
}

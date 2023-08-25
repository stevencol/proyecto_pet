package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetTypeDtoName {


    public PetTypeDtoName(){


    }




    public PetTypeDtoName(String name){

        this.name =name;
    }

    @NotNull(message =  "El nombre es requerido")
    @Size(min = 3 , message = "El tipo debe contener minimo 4 Caracteres")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @JsonProperty("name")
    private String name;
}

package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.valid.TypeBean;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RaceDtoSave
{


    @Size(min = 5 , max = 25, message = "*Mínimo 6 caracteres Máximo 20")
    @NotEmpty(message = "*No puede estar vacio")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @JsonProperty("name")
    private  String name;


    @JsonProperty("type")
    @TypeBean
    private PetTypeUseId type;

}

package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.enums.Sexo;
import lombok.Data;


import java.util.List;

@Data public class PetGetDto {

    @JsonProperty("id")
    private Long id;



    @JsonProperty("name")
    private String name;


    @JsonProperty("age")
    private String edad;



    @JsonProperty("race")
    private RaceDtoName race;


    @JsonProperty("img")
    private List<ImgsForPetGet> img;

    @JsonProperty("type")
    private PetTypeDtoName petType;

    @JsonProperty("sex")
    private Sexo sexo;
    @JsonProperty("description")
    private String description;
}

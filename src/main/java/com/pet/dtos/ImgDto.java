package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.entitys.Pet;
import jakarta.persistence.*;

public class ImgDto {


    @JsonProperty("id")
    private Long id;



    @JsonProperty("urls")
    private String urls;

  @JsonProperty("pet")
    private Pet pet;

}

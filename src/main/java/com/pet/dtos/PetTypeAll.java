package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data public class PetTypeAll {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}

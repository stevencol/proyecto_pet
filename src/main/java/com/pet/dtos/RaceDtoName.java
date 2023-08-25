package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class RaceDtoName {

    @JsonProperty("name")
    private String name;

    public RaceDtoName(String name) {
        this.name = name;
    }
}

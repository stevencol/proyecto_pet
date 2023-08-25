package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonGetId {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("role")
    private List<String> role;

}

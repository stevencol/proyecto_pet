package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PetTypePut {
    

    public PetTypePut() {
    }

    public PetTypePut(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty("id")
    private Long id;



    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private PetTypeUseId type;

}

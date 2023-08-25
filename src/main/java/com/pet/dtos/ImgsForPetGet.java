package com.pet.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImgsForPetGet {
    public ImgsForPetGet(String url) {
        this.url = url;
    }


    @JsonProperty("url")
    private String url;

    @JsonProperty("idCloud")
    private  String idCloud;
}

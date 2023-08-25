package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data public class RaceUseID {

    @JsonProperty("id")
    private Long id;

}

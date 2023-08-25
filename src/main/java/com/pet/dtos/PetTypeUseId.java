package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PetTypeUseId {



    @NotNull(message = "Ingesa  un id valido para persona")
    @JsonProperty("id")
    private Long id;
}

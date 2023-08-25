package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PetUseId {

    @NotNull
    @JsonProperty("id")
    private Long id;
}

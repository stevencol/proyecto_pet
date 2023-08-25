package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonUseIDd {
    @JsonProperty("id")
    @NotNull(message = "Id de Usuario resquerido")
    private Long id;

}

package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationPutDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private String status;
}

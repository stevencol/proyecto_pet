package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.enums.ApplicationStatus;
import com.pet.valid.PersonBean;
import com.pet.valid.PetBean;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ApplicationsPostDto {




    @JsonProperty("person")
    private PersonUseIDd person;

    @JsonProperty("pet")
    private PetUseId pet;

    @JsonProperty("status")
    private ApplicationStatus status;

    @NotNull
    @Pattern(regexp = "[0-9]*", message = "Solo valores numericos")
    @JsonProperty("document")
    private  String document;

    @JsonProperty("alternativePhone")
    @Pattern(regexp = "[0-9]*", message = "Solo valores numericos")
    @Size(max =10 ,min = 10 , message = "Debe  contener 10 digitos")
    private String alternativePhone;

    @JsonProperty("address")
    @NotNull
    @Size(min = 5, message = "Dirrecion demasiado corta")
    private String address;

    @JsonProperty("adoptionMotivation")
    @NotNull
    @Size(min = 5, message = "Motivo demasiado corto")
    private  String adoptionMotivation;
}

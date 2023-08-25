package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.entitys.Person;
import com.pet.entitys.Pet;
import com.pet.enums.ApplicationStatus;
import lombok.Data;
import lombok.ToString;


@Data
public class ApplicationsGetDto {

@JsonProperty("id")
    private  Long id;


    @JsonProperty("person")
    private PersonGetPostSImple person;

    @JsonProperty("pet")
    private PetGetDto pet;

    @JsonProperty("status")
    private ApplicationStatus status;

    @JsonProperty("document")
    private  String document;

    @JsonProperty("alternativePhone")
    private String alternativePhone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("adoptionMotivation")
    private  String adoptionMotivation;
}

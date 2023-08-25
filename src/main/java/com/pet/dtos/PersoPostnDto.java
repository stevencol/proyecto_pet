package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.valid.EmailBean;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data public class PersoPostnDto {

   @JsonProperty("id")
   private Long id;

    @JsonProperty("firtsName")
    @Pattern(regexp = "[a-zA-Z ]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "El nombre debe Contener Minimo", min =3)
    private String firtsName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("lastName")
    @Pattern(regexp = "[a-zA-ZnÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "El apellido debe Contener Minimo", min =3)
    private String lastName;

    @JsonProperty("email")
    @EmailBean
    private String email;


    @JsonProperty("phone")
    @Pattern(regexp = "[0-9]*" , message = "Solo se admiente valores numericos")
    @Size(message = "El Telefono debe ser 10 digitos", min =10, max = 10)
    private String phone;

 @Size(min = 6 , max = 12 , message = "La contraseña debe contener minimo 6 caracteres y maximo 12")
    @Column(name = "password")
 private String password;
}

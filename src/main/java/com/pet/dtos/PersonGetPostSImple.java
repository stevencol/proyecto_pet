package com.pet.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.entitys.Applications;
import com.pet.entitys.Role;
import com.pet.enums.Roles;
import com.pet.valid.EmailBean;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PersonGetPostSImple {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("firtsName")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "El nombre debe Contener Minimo", min =3)
    private String firtsName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("lastName")
    @Pattern(regexp = "[a-zA-ZñÑ ]*" , message = "Solo usa caracteres alfabéticos ")
    @Size(message = "El apellido debe Contener Minimo", min =3)
    private String lastName;

    @JsonProperty("email")
    @EmailBean
    private String email;


    @JsonProperty("phone")
    @Pattern(regexp = "[0-9]*" , message = "Solo se admiente valores numericos")
    @Size(message = "El Telefono debe ser 10 digitos", min =10, max = 10)
    private String phone;

    @JsonProperty("password")
    private String password;

    @Column(name = "rol")
    private List <String> roles;


}

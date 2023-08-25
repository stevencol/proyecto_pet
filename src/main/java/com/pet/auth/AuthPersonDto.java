package com.pet.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
 public class AuthPersonDto {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private  String password;
}

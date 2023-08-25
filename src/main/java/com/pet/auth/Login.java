package com.pet.auth;

import com.pet.services.servicesImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {


    @Autowired
    Auth auth;

    @Autowired
    PersonServiceImpl personService;


@PostMapping("/login")
public ResponseEntity<?> Login(@RequestBody  AuthPersonDto authPersonDto){


    System.out.println(authPersonDto.getEmail());
    System.out.println(authPersonDto.getPassword());
                return  ResponseEntity.ok(auth.signIng(authPersonDto));

            }
}

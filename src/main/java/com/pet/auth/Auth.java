package com.pet.auth;

import com.pet.dtos.PersonGetPostSImple;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class Auth {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AutProvider provider;
    @Autowired
    MapperStruct mapperStruct;


    public JwtResponseDto signIng(AuthPersonDto personDto){
        Optional<PersonGetPostSImple> person = Optional.ofNullable(personService.findByEmailLogin(personDto.getEmail()));
        System.out.println(person.get().getEmail());
        System.out.println(person.get().getPassword());
        if (person.isEmpty()){
            return  null;
        }
        if(!passwordEncoder.matches(personDto.getPassword() , person.get().getPassword())){
            return null;}
return  new JwtResponseDto(provider.createToken(person.get()));
    }


}

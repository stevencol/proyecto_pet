package com.pet.services.servicesIa;

import com.pet.dtos.PersoPostnDto;
import com.pet.dtos.PersonGetId;
import com.pet.dtos.PersonGetPostSImple;
import com.pet.entitys.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonServiceI {

    PersoPostnDto findById(Person personDto);

    Person  save(Person person );
    Person  delteById(Long id);
     Person findById(Long id);
    List <Person> findAll();
    Person finByUserAndPassword (String password, String email);
}

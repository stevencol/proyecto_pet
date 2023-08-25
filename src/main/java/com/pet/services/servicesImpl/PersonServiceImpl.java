package com.pet.services.servicesImpl;

import com.pet.dtos.PersoPostnDto;
import com.pet.dtos.PersonGetId;
import com.pet.dtos.PersonGetPostSImple;
import com.pet.entitys.Person;
import com.pet.mappers.MapperStruct;
import com.pet.repository.PersonRepo;
import com.pet.services.servicesIa.PersonServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonServiceI {

    @Autowired
    PersonRepo personRepo;

    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
    MapperStruct mapperStruct;




    @Override
    public PersoPostnDto findById(Person personDto) {
        return null;
    }



    @Override
    public Person save(Person person) {
    person.setPassword(passwordEncoder.encode(person.getPassword()));

        return personRepo.save(person);
    }

    @Override
    public Person delteById(Long id) {
        return null;
    }

    @Override
    public Person findById(Long  id) {
     return    personRepo.findById(id).orElse(null);
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons =personRepo.findAll();
     return  persons;
    }

    @Override
    public Person finByUserAndPassword(String password, String email) {
        return personRepo.finByUserAndPassword(password ,email );
    }


    public int  findByEmail(String email) {

        return personRepo.findRegister(email);
    }

    public PersonGetPostSImple  findByEmailLogin(String email) {

        return mapperStruct.getPerson(personRepo.findByEmail(email));
    }
}

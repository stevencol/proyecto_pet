package com.pet.controllers;



import com.pet.auth.AuthPersonDto;
import com.pet.dtos.PersoPostnDto;
import com.pet.dtos.PersonGetPostSImple;
import com.pet.entitys.Person;
import com.pet.entitys.Role;
import com.pet.entitys.UserRoles;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesIa.IRolesUser;
import com.pet.services.servicesImpl.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/person")
@CrossOrigin("*")
public class PersonConrtroller {

    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private MapperStruct mapperStruct;
    @Autowired
    private IRolesUser userRolservice;


    @PostMapping("/sign-in")
    public ResponseEntity<?> logindos(@RequestBody AuthPersonDto authPersonDto){
        return ResponseEntity.ok(null);

    }

    @PostMapping("/create")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersoPostnDto person, BindingResult result) {
        Person personEntity;


        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (person == null) {
            response.put("message", "Estas enviando un dato invalido");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        try {
            personEntity = mapperStruct.savePerson(person);
            personService.save(personEntity);

            Role role  = new Role();
            role.setId(1);

            UserRoles userRoles = new UserRoles();
            userRoles.setPerson(personEntity);
            userRoles.setRole(role);

            userRolservice.save(userRoles);

            response.put("person", personEntity);
            response.put("message", "Se guardo Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {

            response.put("message", "Se produjo el siguiente error al guardar : " + e.getMostSpecificCause());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }



    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        System.out.println();
        try {
            List<PersonGetPostSImple> persons = mapperStruct.getPersons(personService.findAll());
            System.out.println(persons.size());
            if(persons!=null && persons.size()>0){
                response.put("persons" , persons);
                response.put("message" ,"Se Completo la busqueda Correctamente");
                return  new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                response.put("message" ,"No se encontraon registros");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("message" ,"Se produjo un error al relizar la consulta : " +e.getCause());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }




    @GetMapping("get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {

            PersonGetPostSImple person = mapperStruct.getPerson(personService.findById(id));
            if (person!=null) {
                response.put("person", person);
                response.put("message", "Se Completo la busqueda Correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "No se encontraon registros");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.put("message", "Error al relizar la consulta : " +e.getCause() );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }catch (NullPointerException e){
            response.put("message", "Error al relizar la consulta : " +e.getCause() );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody PersoPostnDto person , BindingResult result, @PathVariable Long id){
        System.out.println(id);
        Map<String, Object> response = new HashMap<>();
        Person personEntity;


        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if(person.getId()!=id){
            response.put("message", "Los ids No coinciden");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }



        if (person == null) {
            response.put("message", "Estas enviando un dato invalido");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


            try
            {personEntity=   mapperStruct.savePerson(person);
                personEntity.setId(id);
                personService.save(personEntity);
                response.put("person", personEntity);
                response.put("message", "Se Edito Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {

            response.put("message", "Se produjo el siguiente error al guardar : " + e.getMostSpecificCause());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("/get/{password}/{email}")
    public ResponseEntity<?> getLogin(@PathVariable String password, @PathVariable String email){


        Map<String, Object> response = new HashMap<>();
        PersonGetPostSImple personGetId ;


        try {


            personGetId= mapperStruct.getPerson(personService.finByUserAndPassword(password,email));
            System.out.println(personGetId);


            if(personGetId.getId()!=null)
                response.put("message" , "Inicio Correcto");
                response.put("persons" , personGetId);
                return  new ResponseEntity(response,HttpStatus.OK);

        }catch (DataAccessException e){
            response.put("message", "Error al traer registro: " + e.getCause());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NullPointerException e){
            response.put("message", "Error al traer registro: " + e.getCause());
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public void test(){
        System.out.println(personService.findByEmail("steven2088@hotmail.es"));
    }


    }





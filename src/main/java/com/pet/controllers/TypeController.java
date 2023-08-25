package com.pet.controllers;


import com.pet.dtos.PetTypeAll;
import com.pet.dtos.PetTypeDtoName;
import com.pet.entitys.PetType;
import com.pet.entitys.Raza;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesIa.PetTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/type")
@CrossOrigin("*")
public class TypeController {

    @Autowired
    private MapperStruct mapperStruct;

    @Autowired
    private PetTypeService petTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> saveType(@Valid @RequestBody PetTypeDtoName type, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {


                response.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else if (type == null) {
            response.put("message", "Enviaste un objeto Vacio");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {

            try {
                PetType typeEntity = new PetType();
                typeEntity.setName(type.getName());
                System.out.println(typeEntity);
                petTypeService.save(typeEntity);
                response.put("message", "El registro se guardo Correctemente ");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } catch (DataAccessException e) {
                response.put("message", "Se produjo un error al guardar :" + e.getCause());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getTypes() {
        Map<String, Object> response = new HashMap<>();
        List<PetTypeAll> getTypes = new ArrayList<>();

        try {
            if (petTypeService.getAll() != null) {
                getTypes = mapperStruct.getTypes(petTypeService.getAll());
                response.put("types", getTypes);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("message", "No se encotraron registros");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            response.put("message", "Se produjo un error al relizar la consulta : " + e.getMostSpecificCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getType(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (petTypeService.findById(id) != null) {
                PetType petType = petTypeService.findById(id);
                PetTypeAll type = new PetTypeAll();
                type.setId(petType.getId());
                type.setName(petType.getName());
                response.put("types", type);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("message", "No se encontro el registro");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            response.put("message", "Se produjo un error al relizar la consulta : " + e.getMostSpecificCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> uptade(@PathVariable Long id  , @Valid @RequestBody PetTypeDtoName type, BindingResult result) {

        Map<String , Object> response  = new HashMap<>();

        if(result.hasErrors()){
            result.getFieldErrors().forEach(error->{
                response.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(petTypeService.findById(id)==null){
            response.put("message" , "No se encontro ningun Registro");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            PetType typeEntity = petTypeService.findById(id);
            typeEntity.setName(type.getName());

            petTypeService.save(typeEntity);
            response.put("message", "Se Edito el registro corectamente");
            return  new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
     response.put("message" ,"Se genero un error al  editar el registo : "+e.getCause());
     return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}
package com.pet.controllers;

import com.pet.dtos.PetTypePut;
import com.pet.dtos.RaceDtoAll;
import com.pet.dtos.RaceDtoName;
import com.pet.dtos.RaceDtoSave;
import com.pet.entitys.Raza;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesIa.RazaServiceIa;
import com.pet.services.servicesImpl.RazaServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/race")
@CrossOrigin("*")
public class RazaController {
    @Autowired
    private MapperStruct mapperStruct;

    @Autowired
    private RazaServiceIa razaService;
    @Autowired
    private RazaServiceImpl serviceRace;

    @PostMapping("/create")
    public ResponseEntity<?> saveRace(@Valid @RequestBody RaceDtoSave raza, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {

                response.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {

            Raza raceSave = serviceRace.save(mapperStruct.saveRaza(raza));
            response.put("race", new RaceDtoName(raza.getName()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        } catch (DataAccessException e) {

            response.put("message", "Error al gurdar raza:  " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getRaces() {


        Map<String, Object> response = new HashMap<>();

        try {
            List<RaceDtoAll> races = mapperStruct.getRaces(razaService.findAll());
            response.put("races", races);
            if (races == null) {
                response.put("message", "No se encontraron registros");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

            }

        } catch (DataAccessException e) {
            response.put("message",
                    "Se genero un error al relizar la consulta: " + e.getMostSpecificCause().getCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRace(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            RaceDtoAll race = mapperStruct.getRace(razaService.findById(id));
            if (race == null) {
                response.put("message", "No se encontro el registro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("race", race);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("message",
                    "Se genero un error al relizar la consulta: " + e.getMostSpecificCause().getCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateRaza(@Valid @RequestBody PetTypePut race, @PathVariable Long id,
            BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {

                response.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Raza raceEntity = razaService.findById(id);

            if (raceEntity == null) {
                response.put("message", "No se encontro ningiun registro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                raceEntity.setName(race.getName());
                raceEntity.setId(id);
                raceEntity.setType(mapperStruct.getTypeId(race.getType()));
                response.put("race", mapperStruct.getRace(razaService.save(raceEntity)));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

        } catch (DataAccessException ex) {
            response.put("message", "Se produjo un error al actulizar el registro" + ex.getCause());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/siry se{id}")
    public ResponseEntity<?> deleteRaza(@PathVariable Long id) {
        System.out.println(id);
        Map<String, Object> response = new HashMap<>();

        try {
            Raza raza = razaService.findById(id);

            if (raza == null) {
                response.put("message", "No se encotro ningun Registro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                razaService.delte(id);
                response.put("message", "Registro eliminado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException ex) {
            response.put("message", "Error al eliminar registro : " + ex.getCause());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}

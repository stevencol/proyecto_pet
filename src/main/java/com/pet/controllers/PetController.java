package com.pet.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pet.dtos.*;
import com.pet.entitys.Pet;
import com.pet.enums.Sexo;
import com.pet.errros.PetValidationSexo;
import com.pet.mappers.MapperStruct;
import com.pet.repository.ImgRepo;
import com.pet.services.servicesImpl.CloudinariServiceImpl;
import com.pet.services.servicesImpl.PetServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin("*")
public class PetController {

    @Autowired
    private MapperStruct mapperStruct;

    @Autowired
    private PetServiceImpl petService;
    @Autowired
    private ImgRepo imgRepo;

    @Autowired
    CloudinariServiceImpl cloudinariService;

    /***
     *
     * PetPostDto
     * para el registro de mascotas
     * <p>
     * Respuesta de entida registrada
     * </p>
     */
    @PostMapping("/create")
    public ResponseEntity<?> savePet(@Valid @RequestBody PetPostDto pet, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        System.out.println(pet.getSex());


        if (result.hasErrors() || !pet.getSex().equals("MACHO") && !pet.getSex().equals("HEMBRA")  ) {
             if(!pet.getSex().equals("MACHO") && !pet.getSex().equals("HEMBRA")){
                 result.addError(new FieldError("sex", "sex", "Sexo no valido"));
             }
            for (FieldError error : result.getFieldErrors()) {

                response.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else if (pet == null) {
            response.put("message", "Estas intentando guardar un dato invalido");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Pet petF = petService.save(mapperStruct.savePet(pet));
            response.put("message", "El registro se guardo Correctemente");
            response.put("pet", petF);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("message", "Se produjo el siguiente error al guardar : " + e.getLocalizedMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getPets() {

        Map<String, Object> response = new HashMap<>();
        try {
            List<PetGetDto> pets = mapperStruct.getPets(petService.findAll());

            if (pets == null) {
                response.put("message", "No se econtraron resultados");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.put("pets", pets);
                return new ResponseEntity<>(pets, HttpStatus.OK);
            }

        } catch (DataAccessException ex) {
            response.put("message", "Se genero un error al consultar" + ex.getMostSpecificCause().getCause());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPetForId(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        System.out.println(id);
        try {
            PetGetDto pet = mapperStruct.getPet(petService.findById(id));
            System.out.println(pet);

            if (pet == null) {
                response.put("message", "Error: No exite registro para la id : " + id);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            } else {
                response.put("pet", pet);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("message", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        PetGetDto pet = mapperStruct.getPet(petService.findById(id));
        if (pet == null) {
            response.put("message", "El registro existe ");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            petService.deleteById(pet.getId());
            response.put("message", "Se eliminto Correctamente");
            response.put("pet", pet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", "Errror al aliminar : " + e.getCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public  ResponseEntity<?>update(@Valid @RequestBody PetPostDto petGetDto , BindingResult result, @PathVariable Long id ){
        Map<String , Object> response  = new HashMap<>();

        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {


                response.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {

            if(petService.findById(id)==null){
                response.put("message", "No existe Registro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else if(id==petGetDto.getId()  && petService.findById(id)!=null){
                Pet petUpdate = mapperStruct.savePet(petGetDto);
                petUpdate.setId(id);
                petService.save(petUpdate);
                response.put("message" , "Se edito Correctamente");
                response.put("pet" , petUpdate);
                return  new ResponseEntity<>(response, HttpStatus.OK);
            }else  if(petService.findById(id).getId()!=petGetDto.getId()){
                response.put("message" , "Las ids No coinciden");
                return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
            }


        }catch (DataAccessException e){
            response.put("message" , "Se presento un error al actulizar registro: " +e.getCause());
            return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
        }


 return null;
    }


    @PostMapping("/save-imgs/{id}")
    public ResponseEntity<?> setImg(@RequestPart List<MultipartFile> imgs, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (petService.findById(id) == null) {
                response.put("message", "Id no valida");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
            return (cloudinariService.setImgPEt(imgs, petService.findById(id)));
        } catch (Exception e) {
            response.put("message", "Error al guardar Ejecutar Cloudinari servide");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-img/{folder}/{folderpet}/{id}")
    public ResponseEntity<?> deleteImg(@RequestPart List<MultipartFile> imgs, @PathVariable String id,
            @PathVariable String folder, @PathVariable String folderpet) {

        return cloudinariService.delete(folder + "/" + folderpet + "/" + id);
    }

    @PostMapping("/test")
    public void  imprimir(@RequestPart MultipartFile multipartFile  ,@RequestBody PetUseId petId){
        System.out.println(petId);
    }










}

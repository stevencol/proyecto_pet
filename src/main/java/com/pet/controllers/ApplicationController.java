package com.pet.controllers;

import com.pet.dtos.ApplicationPutDto;
import com.pet.dtos.ApplicationsGetDto;
import com.pet.dtos.ApplicationsPostDto;
import com.pet.dtos.PersonGetId;
import com.pet.entitys.Applications;
import com.pet.entitys.Person;
import com.pet.enums.ApplicationStatus;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesIa.ApplicationsServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
private     ApplicationsServiceI appService;

    @Autowired
    private     MapperStruct mapperStruct;



    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ApplicationsPostDto aplication , BindingResult result){
        System.out.println(aplication);
        Map<String , Object> response  = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

           if(aplication==null){
               response.put("message","Estas intentando guarda datos ivalidos");
               return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
           }


           try {
               if(appService.findByPendingForIdPerson(aplication.getPerson().getId())>=3){
                response.put("message" , "No se puenden tener mas solicidutes pendientes, ya tienes: "+ 3 );
                return  new ResponseEntity<>(response ,HttpStatus.UNPROCESSABLE_ENTITY);

               }

               Applications applications = appService.save(mapperStruct.saveAplication(aplication));
               response.put("message" , "Se registro Correctament");
               response.put("aplication" , applications);
               return  new ResponseEntity<>(response , HttpStatus.OK);


           }catch (DataAccessException e){
               response.put("message" , "Se produjo un error al guardar la aplicacion : "+ e.getCause());
               return  new ResponseEntity<>(response , HttpStatus.OK);
           }catch (NullPointerException e){
               response.put("message" , "Se produjo un error al guardar la aplicacion : "+ e.getCause());
               return  new ResponseEntity<>(response , HttpStatus.OK);
           }

    }



    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        Map<String , Object> response  = new HashMap<>();

        try {
            List<ApplicationsGetDto> aplicaApplications = mapperStruct.getAplications(appService.findAll());
            if(aplicaApplications==null){
                response.put("message","No Existen Resgistros");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
                 response.put("application" ,aplicaApplications);
                response.put("message","Se completo correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);


        }catch (Exception e){
            response.put("message","Se produjo el siguiente Error : "  +e.getCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

   }

   @GetMapping("/get/{id}")
    public ResponseEntity<?> getAplicationById(@PathVariable("id") Long id){
         Map<String, Object> response = new HashMap<>();

        if(    appService.findById(id)==null){
             response.put("message", "No existe registro");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try{
            ApplicationsGetDto  aplicationGet= mapperStruct.getAplication(appService.findById(id));
            response.put("mesage" ,"Se completo Correctamente");
            response.put("aplication", aplicationGet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al relizar la busqueda" + e.getCause());
            return  new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NullPointerException e){
            response.put("message", "Error al relizar la busqueda" + e.getCause());
            return  new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }

   }


    @GetMapping("/get/person/{id}")
    public ResponseEntity<?> getAplicationPersonById(@PathVariable("id") Long id){
        Map<String, Object> response = new HashMap<>();

        if(    appService.findApicationForPersonById(id)==null ||  appService.findApicationForPersonById(id).size()<1){
            response.put("message", "No existe registro");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try{
           List  <ApplicationsGetDto>  aplicationsGet= mapperStruct.getAplications(appService.findApicationForPersonById(id));
            response.put("mesage" ,"Se completo Correctamente");
            response.put("application", aplicationsGet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al relizar la busqueda" + e.getCause());
            return  new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NullPointerException e){
            response.put("message", "Error al relizar la busqueda" + e.getCause());
            return  new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upateStatatus(@RequestBody ApplicationPutDto applicationPutDto, @PathVariable("id") Long id, BindingResult result){
        Map<String , Object> response = new HashMap<>();

        if(appService.findById(id)==null){
            response.put("message", "La aplicacion que intentas editar no exite");
            return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
        if (result.hasErrors() || !applicationPutDto.getStatus().equals("PENDING") && !applicationPutDto.getStatus().equals("APPROVED")&&
                !applicationPutDto.getStatus().equals("REJECTED") &&
                !applicationPutDto.getStatus().equals("CANCELLED")) {
            result.addError(new FieldError("status" ,"status" ,"El estado no es valido"));
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }



        try {
            Applications application = appService.findById(id) ;
            application.setStatus(ApplicationStatus.valueOf(applicationPutDto.getStatus()));
           ApplicationsGetDto applicationsGetDto = mapperStruct.getAplication(appService.save(appService.save(application)));

        response.put("message", "El estado se actualizo correctamente ");
        response.put("application" , applicationsGetDto);
        return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("application" , "Se produjo un error al editar : " +e.getCause());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NullPointerException e){
            response.put("application" , "Se produjo un error al editar : " +e.getCause());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get/status/{status}")
    public ResponseEntity <?> getByStatus(@PathVariable("status") String status) {
        Map<String, Object> response = new HashMap<>();

        if (!status.equals(String.valueOf(ApplicationStatus.PENDING)) && !status.equals("APPROVED") &&
                !status.equals("REJECTED") &&
                !status.equals("CANCELLED")) {
            response.put("status" , "status no valido");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


        }
         try {
             List<ApplicationsGetDto> applicationStatus = mapperStruct.getAplications(appService.findAllbyStatus(status));
             if(applicationStatus.size()<1){
                 response.put("message", "No hay registros para ese estado");
                 return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
             }
             response.put("message", "Se realizo Correctamete");
             response.put("aplications", applicationStatus);
             return  new ResponseEntity<>(response, HttpStatus.OK);
         }catch (DataAccessException e){
             response.put("message", "Error al obtener por estado : " + e.getMessage());
             return  new ResponseEntity<>(response, HttpStatus.OK);
         }

    }


    @GetMapping("/get/{status}/{id}")
    public ResponseEntity <?> getByStatusAnUserId(@PathVariable("status") String status ,@PathVariable("id") Long id ) {
        Map<String, Object> response = new HashMap<>();

        if (!status.equals(String.valueOf(ApplicationStatus.PENDING)) && !status.equals("APPROVED") &&
                !status.equals("REJECTED") &&
                !status.equals("CANCELLED")) {
            response.put("status" , "status no valido");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


        }
        try {
            List<ApplicationsGetDto> applicationStatus = mapperStruct.getAplications(appService.findByStatusAnUserId(id ,status));
            if(applicationStatus.size()<1){
                response.put("message", "No hay registros para ese estado");
                return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("message", "Se realizo Correctamete");
            response.put("aplications", applicationStatus);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al obtener por estado : " + e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

}

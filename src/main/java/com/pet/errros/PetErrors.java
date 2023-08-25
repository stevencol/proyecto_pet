package com.pet.errros;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PetErrors {


    @ExceptionHandler(PetValidationSexo.class)
    public ResponseEntity<Map<String, String>> sexValid(PetValidationSexo sexoValid){
                return   ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("sex", sexoValid.getMessage()));

            }


/*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        Map<String,Object>   response= new HashMap<>();
        System.out.println( ex.getCause().getLocalizedMessage().contains("Sex"));

        if(ex.getCause().getLocalizedMessage().contains("sex")){
            response.put("message","Error al selecionar el sexo de la mascota");
            return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
        }else if(ex.getCause().getMessage().contains("PetType")){
            response.put("message","Estas enviando datos incorectos en el cuerpo de 'Tipo'");
            return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);

        }

        response.put("message","Estas enviando Datos Incorrectos "+ ex.getMostSpecificCause());
        return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);



    }*/

    @ExceptionHandler(NumberFormatException.class)
    public  ResponseEntity<?> errorForString(NumberFormatException ex){
        Map<String,Object>   response= new HashMap<>();

            response.put("message","solo se permienten valores numericos");
            return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public  ResponseEntity<?> fileZizeError(MaxUploadSizeExceededException ex){
        Map<String,Object>   response= new HashMap<>();

        response.put("message","Los archivos superan el tamaño permitido");
        return new ResponseEntity<Map<String,Object>>(response , HttpStatus.PAYLOAD_TOO_LARGE);


    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> pageNoFound(NoHandlerFoundException ex){
        Map<String,Object>   response= new HashMap<>();
        response.put("message","Upsss, la ruta que ingresaste no existe");
        return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> pageNoFound1(HttpRequestMethodNotSupportedException ex){
        Map<String,Object>   response= new HashMap<>();
        response.put("message","Upsss, Solicitud equivocada : " + ex.getLocalizedMessage());
        return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NOT_FOUND);
    }









}

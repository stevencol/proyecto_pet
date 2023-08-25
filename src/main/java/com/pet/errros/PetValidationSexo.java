package com.pet.errros;

public class PetValidationSexo extends  RuntimeException {

    public PetValidationSexo(){
        super("Sexo de mascota invalido");
    }
}

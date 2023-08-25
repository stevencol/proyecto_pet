package com.pet.valid;

import com.pet.dtos.PetUseId;
import com.pet.services.servicesIa.PetServiceIa;
import com.pet.services.servicesImpl.PetServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PetValid implements ConstraintValidator<PetBean , PetUseId> {

    @Override
    public boolean isValid(PetUseId value, ConstraintValidatorContext context) {
        PetServiceIa petService = new PetServiceImpl();

        return  petService.findById(value.getId())!=null? true:false;
    }
}

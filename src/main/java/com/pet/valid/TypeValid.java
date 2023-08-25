package com.pet.valid;

import com.pet.dtos.PetTypeUseId;
import com.pet.mappers.MapperStruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeValid implements ConstraintValidator<TypeBean , PetTypeUseId> {

    @Autowired
    MapperStruct mapperStruct ;
    @Override
    public boolean isValid(PetTypeUseId value, ConstraintValidatorContext context) {
        
        return  value.getId() !=null? true : false;
    }
}

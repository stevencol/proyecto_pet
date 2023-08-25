package com.pet.valid;

import com.pet.dtos.RaceUseID;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RaceValid implements ConstraintValidator<ReceValidBean, RaceUseID> {




    @Override
    public boolean isValid(RaceUseID value, ConstraintValidatorContext context) {

        return value.getId()!=null? true : false ;
    }
}

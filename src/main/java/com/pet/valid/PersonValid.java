package com.pet.valid;

import com.pet.dtos.PersonGetPostSImple;
import com.pet.dtos.PersonUseIDd;
import com.pet.entitys.Person;
import com.pet.services.servicesIa.PersonServiceI;
import com.pet.services.servicesImpl.PersonServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PersonValid implements ConstraintValidator<PersonBean , PersonUseIDd> {




    @Override
    public boolean isValid(PersonUseIDd value, ConstraintValidatorContext context) {
         PersonServiceI personService = new PersonServiceImpl();

        return personService.findById(value.getId())!=null? true:false;
    }
}

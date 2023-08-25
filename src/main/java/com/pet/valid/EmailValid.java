package com.pet.valid;

import com.pet.services.servicesImpl.PersonServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class EmailValid implements ConstraintValidator<EmailBean, String> {

    private final static List<String> emails = Arrays.asList("@hotmail.es",
            "@hotmail.com",
            "@outlook.es",
            "@gmail.com",
            "@yahoo.com",
            "@yahoo.es",
            "@icloud.com");
    @Autowired
    PersonServiceImpl personService = new PersonServiceImpl();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        if (value.contains("@")) {

            if (personService.findByEmail(value) > 0) {
                context.buildConstraintViolationWithTemplate("El correo ya esta registrado")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                return false;
            }

            return emails.contains(value.substring(value.lastIndexOf("@")));
        }
        // return value.contains("a")? value.substring(value.lastIndexOf("@",value.length())) :false ;
        return false;

    }
}

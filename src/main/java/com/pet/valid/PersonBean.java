package com.pet.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PersonValid.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonBean {

    String message() default "Persona no valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

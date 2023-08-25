package com.pet.valid;

import jakarta.validation.Payload;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RaceValid.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface ReceValidBean {
    String message() default "Raza no valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

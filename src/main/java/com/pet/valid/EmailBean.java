package com.pet.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EmailValid.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface EmailBean {
    String message() default "Correo no permitido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

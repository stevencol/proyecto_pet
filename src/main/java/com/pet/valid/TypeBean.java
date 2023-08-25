package com.pet.valid;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TypeValid.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeBean {


    String message() default "El tipo de mascota es requerido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

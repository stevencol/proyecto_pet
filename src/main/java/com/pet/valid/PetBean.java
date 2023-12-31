package com.pet.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PetValid.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PetBean {



    String message() default "Mascota  no valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

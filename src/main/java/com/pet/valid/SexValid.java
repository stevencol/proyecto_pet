

package com.pet.valid;

import com.pet.enums.Sexo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SexValid  implements ConstraintValidator<SexBean, Sexo> {




    @Override
    public void initialize(SexBean constraintAnnotation) {
    }

    @Override
    public boolean isValid(Sexo genero, ConstraintValidatorContext context) {
        return genero == Sexo.MACHO || genero == Sexo.HEMBRA;
    }


}

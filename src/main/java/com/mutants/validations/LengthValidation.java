package com.mutants.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class LengthValidation implements ConstraintValidator<Length, List<String>> {

    @Override
    public void initialize(Length constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value.stream()
                        .filter(element -> element.length() != value.size())
                        .collect(Collectors.toList()).size() == 0;
    }
}

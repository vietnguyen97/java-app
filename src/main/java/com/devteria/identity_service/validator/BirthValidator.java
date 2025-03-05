package com.devteria.identity_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class BirthValidator implements ConstraintValidator<BirthConstraints, LocalDate> {
    private int min;

    // Hàm xử lý data có đúng ko
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return true;
        long years = ChronoUnit.YEARS.between(value, LocalDate.now());
        return years >= min;
    }

    @Override
    public void initialize(BirthConstraints constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }
}

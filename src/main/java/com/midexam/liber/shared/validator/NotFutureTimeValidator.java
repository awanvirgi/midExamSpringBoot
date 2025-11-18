package com.midexam.liber.shared.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class NotFutureTimeValidator implements ConstraintValidator<NotFutureTime, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime s, ConstraintValidatorContext constraintValidatorContext){
        if (s == null){
            return true;
        }
        return !s.isAfter(LocalDateTime.now());
    }
}

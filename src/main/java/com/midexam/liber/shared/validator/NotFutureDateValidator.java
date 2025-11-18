package com.midexam.liber.shared.validator;

import com.midexam.liber.shared.enums.ReadStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class NotFutureDateValidator implements ConstraintValidator<NotFutureDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate s, ConstraintValidatorContext constraintValidatorContext){
        if (s == null){
            return true;
        }
        return !s.isAfter(LocalDate.now());
    }
}

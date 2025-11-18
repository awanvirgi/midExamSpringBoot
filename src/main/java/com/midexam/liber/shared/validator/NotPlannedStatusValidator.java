package com.midexam.liber.shared.validator;

import com.midexam.liber.shared.enums.ReadStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotPlannedStatusValidator implements ConstraintValidator<NotPlannedStatus, ReadStatus> {

    @Override
    public boolean isValid(ReadStatus s, ConstraintValidatorContext constraintValidatorContext){
        if (s == null){
            return true;
        }
        return s != ReadStatus.PLANNED;
    }
}

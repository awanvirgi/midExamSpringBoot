package com.midexam.liber.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotPlannedStatusValidator.class)
public @interface NotPlannedStatus {
    String message() default "ReadStatus Cannot Planned";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

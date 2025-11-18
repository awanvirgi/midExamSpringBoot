package com.midexam.liber.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureTimeValidator.class)
public @interface NotFutureTime {

    String message() default "DateTime Cannot Future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

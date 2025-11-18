package com.midexam.liber.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueAuthorValidator.class)
public @interface UniqueAuthorName {
    String message() default "Author Name Is Already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

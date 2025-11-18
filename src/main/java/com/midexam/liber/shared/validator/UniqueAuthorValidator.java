package com.midexam.liber.shared.validator;

import com.midexam.liber.author.AuthorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueAuthorValidator implements ConstraintValidator<UniqueAuthorName,String> {

    private final AuthorRepository authorRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return true;
        }
        // kalau ada JPA langsung tembak aj ke Repositorynya
        return !authorRepository.existsByName(s);
    } // ini wajib
}

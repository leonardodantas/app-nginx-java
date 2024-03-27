package com.br.testes.carga.infra.http.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidCategoriesValidatorAnnotation implements ConstraintValidator<ValidCategoriesAnnotation, List<String>> {

    @Override
    public boolean isValid(final List<String> categories, final ConstraintValidatorContext constraintValidatorContext) {
        if (categories.isEmpty()) {
            return false;
        }

        final var categoriesAllEmpty = categories.stream().allMatch(String::isEmpty);
        if (categoriesAllEmpty) {
            return false;
        }

        return categories.size() <= 5;
    }
}

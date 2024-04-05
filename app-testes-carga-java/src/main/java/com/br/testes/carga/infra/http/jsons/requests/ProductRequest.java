package com.br.testes.carga.infra.http.jsons.requests;

import com.br.testes.carga.infra.http.validations.ValidCategoriesAnnotation;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        @Size(min = 7)
        String name,
        String code,
        @Positive
        BigDecimal value,
        @Size(min = 15)
        String description,
        @ValidCategoriesAnnotation
        List<String> categories
) {
}

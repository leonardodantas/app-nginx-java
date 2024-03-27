package com.br.testes.carga.infra.http.jsons.responses;

import com.br.testes.carga.domains.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        String id,
        String name,
        BigDecimal value,
        String description,
        List<String> values
) {
    public static ProductResponse from(final Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getValue(),
                product.getDescription(),
                product.getCategoriesAsListString()
        );
    }
}

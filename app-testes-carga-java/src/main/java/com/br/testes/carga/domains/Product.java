package com.br.testes.carga.domains;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class Product {

    private String id;
    private String name;
    private BigDecimal value;
    private String description;
    private Categories categories;

    public List<String> getCategoriesAsListString() {
        return categories.getValue().stream().map(String::toUpperCase).toList();
    }
}

package com.br.testes.carga.infra.database.postgres.specifications;

import com.br.testes.carga.infra.database.postgres.entities.CategoriesEntity;
import com.br.testes.carga.infra.database.postgres.entities.ProductEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CategoriesEntitySpecification {

    public static Specification<ProductEntity> containsCategories(final List<String> values) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, CategoriesEntity> categoriesEntityJoin = root.join("categories");
            return categoriesEntityJoin.get("values").in(values);
        };
    }
}

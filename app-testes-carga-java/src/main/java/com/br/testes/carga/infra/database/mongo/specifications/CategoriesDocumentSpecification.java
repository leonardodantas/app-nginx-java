package com.br.testes.carga.infra.database.mongo.specifications;

import com.br.testes.carga.infra.database.mongo.documents.CategoriesDocument;
import com.br.testes.carga.infra.database.mongo.documents.ProductDocument;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CategoriesDocumentSpecification {

    public static Specification<ProductDocument> containsCategories(final List<String> values) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductDocument, CategoriesDocument> categoriesEntityJoin = root.join("categories");
            return categoriesEntityJoin.get("values").in(values);
        };
    }
}

package com.br.testes.carga.infra.database.mongo.converter;

import com.br.testes.carga.domains.Categories;
import com.br.testes.carga.domains.Product;
import com.br.testes.carga.infra.database.mongo.documents.CategoriesDocument;
import com.br.testes.carga.infra.database.mongo.documents.ProductDocument;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDocumentConverter implements Converter<ProductDocument, Product> {

    @Override
    public Product convert(final ProductDocument document) {
        return Product.builder()
                .id(document.getId())
                .code(document.getCode())
                .name(document.getName())
                .value(document.getValue())
                .description(document.getDescription())
                .categories(convert(document.getCategories()))
                .build();
    }

    private Categories convert(final CategoriesDocument document) {
        return Categories.builder()
                .id(document.getId())
                .value(document.getValues())
                .build();
    }
}

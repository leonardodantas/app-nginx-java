package com.br.testes.carga.infra.database.postgres.converter;

import com.br.testes.carga.domains.Categories;
import com.br.testes.carga.domains.Product;
import com.br.testes.carga.infra.database.postgres.entities.CategoriesEntity;
import com.br.testes.carga.infra.database.postgres.entities.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity entity) {
        return Product.builder()
                .id(String.valueOf(entity.getId()))
                .name(entity.getName())
                .value(entity.getValue())
                .description(entity.getDescription())
                .categories(convert(entity.getCategories()))
                .build();

    }

    private Categories convert(final CategoriesEntity entity) {
        return Categories.builder()
                .id(String.valueOf(entity.getId()))
                .value(entity.getValues())
                .build();
    }
}

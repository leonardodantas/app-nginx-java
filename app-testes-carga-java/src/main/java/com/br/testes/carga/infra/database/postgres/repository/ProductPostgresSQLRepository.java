package com.br.testes.carga.infra.database.postgres.repository;

import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import com.br.testes.carga.infra.database.postgres.converter.ProductEntityConverter;
import com.br.testes.carga.infra.database.postgres.entities.CategoriesEntity;
import com.br.testes.carga.infra.database.postgres.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.br.testes.carga.infra.database.postgres.specifications.CategoriesEntitySpecification.containsCategories;

@Repository
@RequiredArgsConstructor
public class ProductPostgresSQLRepository implements IProductRepository {

    private final ProductPostgresRepository productPostgresRepository;
    private final ProductEntityConverter productEntityConverter;

    @Override
    public Product save(final Product product) {
        final var productEntity = ProductEntity.from(product);
        final var categoriesEntity = CategoriesEntity.from(product);
        final var productToSave = productEntity.withCategories(categoriesEntity);
        final var productSave = productPostgresRepository.save(productToSave);
        return productEntityConverter.convert(productSave);
    }

    @Override
    public Product findById(final String productId) {
        return productPostgresRepository.findById(Long.valueOf(productId))
                .map(productEntityConverter::convert)
                .orElseThrow(() -> new ProductNotFoundException(String.format("ProductId %s not found in PostgresSQL", productId)));
    }

    @Override
    public List<Product> findAllProductsByCategories(final List<String> values) {
        return productPostgresRepository.findAll(containsCategories(values))
                .stream().map(productEntityConverter::convert)
                .toList();

    }

    @Override
    public int getQuantityItems() {
        return Long.valueOf(productPostgresRepository.count()).intValue();
    }

    @Override
    public void deleteAll() {
        productPostgresRepository.deleteAll();
    }

    @Override
    public Optional<Product> findByCodeIgnoreCase(final String code) {
        return productPostgresRepository.findByCodeIgnoreCase(code)
                .map(productEntityConverter::convert);
    }
}

package com.br.testes.carga.app.repositories;

import com.br.testes.carga.domains.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    Product save(final Product product);

    Product findById(final String productId);

    List<Product> findAllProductsByCategories(final List<String> values);

    int getQuantityItems();

    void deleteAll();

    Optional<Product> findByCodeIgnoreCase(final String code);
}

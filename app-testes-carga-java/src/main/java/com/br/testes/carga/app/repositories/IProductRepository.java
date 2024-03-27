package com.br.testes.carga.app.repositories;

import com.br.testes.carga.domains.Product;

import java.util.List;

public interface IProductRepository {

    Product save(final Product product);

    Product findById(final String productId);

    List<Product> findAllProductsByCategories(final List<String> values);

    int getQuantityItems();

    void deleteAll();
}

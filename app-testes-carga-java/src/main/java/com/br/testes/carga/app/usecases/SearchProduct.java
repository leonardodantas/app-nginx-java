package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProduct {

    private final IProductRepository productMongoDBRepository;
    private final IProductRepository productPostgresSQLRepository;

    public List<Product> findByCategoriesV1(final List<String> values) {
        return productMongoDBRepository.findAllProductsByCategories(values);
    }

    public List<Product> findByCategoriesV2(final List<String> values) {
        return productPostgresSQLRepository.findAllProductsByCategories(values);
    }
}

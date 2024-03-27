package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final IProductRepository productMongoDBRepository;
    private final IProductRepository productPostgresSQLRepository;

    public Product createV1(final Product product) {
        return productMongoDBRepository.save(product);
    }

    public Product createV2(final Product product) {
        return productPostgresSQLRepository.save(product);

    }
}

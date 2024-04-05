package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import org.springframework.stereotype.Service;

@Service
public class GetProduct {

    private final IProductRepository productMongoDBRepository;
    private final IProductRepository productPostgresSQLRepository;

    public GetProduct(final IProductRepository productMongoDBRepository, final IProductRepository productPostgresSQLRepository) {
        this.productMongoDBRepository = productMongoDBRepository;
        this.productPostgresSQLRepository = productPostgresSQLRepository;
    }

    public Product getByIdV1(final String productId) {
        return productMongoDBRepository.findById(productId);
    }

    public Product getByIdV2(final String productId) {
        return productPostgresSQLRepository.findById(productId);
    }

    public Product getByCodeV1(final String productCode) {
        return productMongoDBRepository.findByCodeIgnoreCase(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product code %s not found", productCode)));
    }

    public Product getByCodeV2(final String productCode) {
        return productPostgresSQLRepository.findByCodeIgnoreCase(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product code %s not found", productCode)));
    }
}

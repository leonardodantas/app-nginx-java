package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProduct {

    private final IProductRepository productMongoDBRepository;

    public Product getById(final String productId) {
        return productMongoDBRepository.findById(productId);
    }

    public Product getByCode(final String productCode) {
        return productMongoDBRepository.findByCodeIgnoreCase(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product code %s not found", productCode)));
    }

}

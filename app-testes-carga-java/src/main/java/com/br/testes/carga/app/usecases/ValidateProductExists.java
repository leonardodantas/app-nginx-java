package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.exceptions.ProductCodeAlreadyExist;
import com.br.testes.carga.app.repositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidateProductExists {

    public void validate(final String code, final IProductRepository productRepository) {
        productRepository.findByCodeIgnoreCase(code)
                .ifPresent(product -> {
                    throw new ProductCodeAlreadyExist(String.format("Product code %s already exist", code));
                });
    }
}

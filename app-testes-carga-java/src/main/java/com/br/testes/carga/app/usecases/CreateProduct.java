package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final IProductRepository productMongoDBRepository;
    private final ValidateProductExists validateProductExists;

    public Product create(final Product product) {
        validateProductExists.validate(product.getCode(), productMongoDBRepository);
        return productMongoDBRepository.save(product);
    }

}

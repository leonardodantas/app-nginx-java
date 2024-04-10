package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.DatabaseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigProduct {

    private final IProductRepository productMongoDBRepository;

    private static final String MONGODB = "MONGODB";

    public DatabaseInfo getDatabaseInfo() {
        final var mongoQuantity = productMongoDBRepository.getQuantityItems();
        return DatabaseInfo.of(MONGODB, mongoQuantity);
    }

    public void resetDatabases() {
        productMongoDBRepository.deleteAll();
    }
}

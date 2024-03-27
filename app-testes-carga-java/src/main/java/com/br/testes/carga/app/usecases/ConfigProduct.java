package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.DatabaseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigProduct {

    private final IProductRepository productMongoDBRepository;
    private final IProductRepository productPostgresSQLRepository;

    private static final String MONGODB = "MONGODB";
    private static final String POSTGRESSQL = "POSTGRESSQL";

    public List<DatabaseInfo> getDatabaseInfo() {
        final var mongoQuantity = productMongoDBRepository.getQuantityItems();
        final var databaseMongo = DatabaseInfo.of(MONGODB, mongoQuantity);

        final var postgresSQLQuantity = productPostgresSQLRepository.getQuantityItems();
        final var databasePostgres = DatabaseInfo.of(POSTGRESSQL, postgresSQLQuantity);

        return List.of(databaseMongo, databasePostgres);
    }

    public void resetDatabases() {
        productMongoDBRepository.deleteAll();
        productPostgresSQLRepository.deleteAll();
    }
}

package com.br.testes.carga.infra.database.mongo.repository;

import com.br.testes.carga.infra.database.mongo.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> findByCodeIgnoreCase(final String code);
}

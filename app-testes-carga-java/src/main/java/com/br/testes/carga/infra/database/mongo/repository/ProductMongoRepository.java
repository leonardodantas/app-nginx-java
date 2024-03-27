package com.br.testes.carga.infra.database.mongo.repository;

import com.br.testes.carga.infra.database.mongo.documents.ProductDocument;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {
}

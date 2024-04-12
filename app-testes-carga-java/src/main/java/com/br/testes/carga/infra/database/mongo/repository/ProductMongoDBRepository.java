package com.br.testes.carga.infra.database.mongo.repository;

import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import com.br.testes.carga.infra.database.mongo.converter.ProductDocumentConverter;
import com.br.testes.carga.infra.database.mongo.documents.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductMongoDBRepository implements IProductRepository {

    private final ProductMongoRepository productMongoRepository;
    private final ProductDocumentConverter productDocumentConverter;
    private final MongoTemplate mongoTemplate;

    @Override
    @CacheEvict("productCache")
    public Product save(final Product product) {
        final var productId = UUID.randomUUID().toString();
        final var document = ProductDocument.of(productId, product);
        final var documentSave = productMongoRepository.save(document);
        return productDocumentConverter.convert(documentSave);
    }

    @Override
    public Product findById(final String productId) {
        return productMongoRepository.findById(productId)
                .map(productDocumentConverter::convert)
                .orElseThrow(() -> new ProductNotFoundException(String.format("ProductId %s not found in MongoDB", productId)));
    }

    @Override
    public List<Product> findAllProductsByCategories(final List<String> values) {
        final var query = new Query();
        query.addCriteria(Criteria.where("categories.values").in(values));

        return mongoTemplate.find(query, ProductDocument.class)
                .stream().map(productDocumentConverter::convert)
                .toList();
    }

    @Override
    public int getQuantityItems() {
        return Long.valueOf(productMongoRepository.count()).intValue();
    }

    @Override
    public void deleteAll() {
        productMongoRepository.deleteAll();
    }

    @Override
    @Cacheable("productCache")
    public Optional<Product> findByCodeIgnoreCase(final String code) {
        return productMongoRepository.findByCodeIgnoreCase(code)
                .map(productDocumentConverter::convert);
    }
}

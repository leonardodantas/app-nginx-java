package com.br.testes.carga.app.usecases;

import com.br.testes.carga.app.repositories.IProductRepository;
import com.br.testes.carga.domains.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final IProductRepository productMongoDBRepository;
    private final ValidateProductExists validateProductExists;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${spring.kafka.topics.create.product}")
    private final String CREATE_PRODUCT_TOPIC;

    public Product create(final Product product) {
        validateProductExists.validate(product.getCode(), productMongoDBRepository);
        try {
            kafkaTemplate.send(CREATE_PRODUCT_TOPIC, objectMapper.writeValueAsString(product));
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

}

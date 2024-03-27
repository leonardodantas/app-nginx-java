package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.usecases.CreateProduct;
import com.br.testes.carga.infra.http.converters.ProductConverter;
import com.br.testes.carga.infra.http.jsons.requests.ProductRequest;
import com.br.testes.carga.infra.http.jsons.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateProductController {

    private final CreateProduct createProduct;
    private final ProductConverter productConverter;

    @PostMapping("v1/create/product")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inserir um produto em uma base de dados do MongoDB")
    public ProductResponse createV1(@Valid @RequestBody final ProductRequest request) {
        final var domain = productConverter.convert(request);
        final var product = createProduct.createV1(domain);
        return ProductResponse.from(product);
    }

    @PostMapping("v2/create/product")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inserir um produto em uma base de dados do PostgresSQL")
    public ProductResponse createV2(@Valid @RequestBody final ProductRequest request) {
        final var domain = productConverter.convert(request);
        final var product = createProduct.createV2(domain);
        return ProductResponse.from(product);
    }
}

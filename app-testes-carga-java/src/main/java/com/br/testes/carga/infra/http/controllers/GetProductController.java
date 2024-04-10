package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.usecases.GetProduct;
import com.br.testes.carga.infra.http.jsons.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetProductController {

    private final GetProduct getProduct;

    public GetProductController(final GetProduct getProduct) {
        this.getProduct = getProduct;
    }

    @GetMapping("v1/product/{productId}/id")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca de um produto pelo productId no MongoDB")
    public ProductResponse getById(@PathVariable final String productId) {
        final var response = getProduct.getById(productId);
        return ProductResponse.from(response);
    }

    @GetMapping("v1/product/{productCode}/code")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca de um produto pelo productCode no MongoDB")
    public ProductResponse getByCode(@PathVariable final String productCode) {
        final var response = getProduct.getByCode(productCode);
        return ProductResponse.from(response);
    }

}

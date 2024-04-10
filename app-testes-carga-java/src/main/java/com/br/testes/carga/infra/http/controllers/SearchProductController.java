package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.usecases.SearchProduct;
import com.br.testes.carga.infra.http.jsons.requests.CategoriesRequest;
import com.br.testes.carga.infra.http.jsons.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchProductController {

    private final SearchProduct searchProduct;

    @GetMapping("v1/categories")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca de uma lista de produtos por uma lista de categorias no MongoDB")
    public List<ProductResponse> searchByCategoriesV1(@RequestBody final CategoriesRequest request) {
        final var response = searchProduct.findByCategories(request.values());
        return response.stream().map(ProductResponse::from).toList();
    }

}

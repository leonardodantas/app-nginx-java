package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.usecases.ConfigProduct;
import com.br.testes.carga.infra.http.jsons.responses.DatabaseInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("config")
public class ConfigProductController {

    private final ConfigProduct configProduct;

    @GetMapping("v1/databases/info")
    @ResponseStatus(HttpStatus.OK)
    public DatabaseInfoResponse getDatabaseInfo() {
        final var response = configProduct.getDatabaseInfo();
        return DatabaseInfoResponse.from(response);
    }

    @DeleteMapping("v1/reset/databases")
    @ResponseStatus(HttpStatus.OK)
    public void resetDatabases() {
        configProduct.resetDatabases();
    }
}

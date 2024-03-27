package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.usecases.ConfigProduct;
import com.br.testes.carga.infra.http.jsons.responses.DatabaseInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("config")
public class ConfigProductController {

    private final ConfigProduct configProduct;

    @GetMapping("databases/info")
    @ResponseStatus(HttpStatus.OK)
    public List<DatabaseInfoResponse> getDatabaseInfo() {
        final var response = configProduct.getDatabaseInfo();
        return response.stream().map(DatabaseInfoResponse::from).toList();
    }

    @DeleteMapping("reset/databases")
    @ResponseStatus(HttpStatus.OK)
    public void resetDatabases(){
        configProduct.resetDatabases();
    }
}

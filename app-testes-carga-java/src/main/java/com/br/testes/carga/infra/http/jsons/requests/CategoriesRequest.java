package com.br.testes.carga.infra.http.jsons.requests;

import java.util.List;

public record CategoriesRequest(
        List<String> values
) {
}

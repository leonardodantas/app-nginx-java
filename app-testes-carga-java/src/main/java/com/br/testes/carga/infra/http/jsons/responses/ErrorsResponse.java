package com.br.testes.carga.infra.http.jsons.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ErrorsResponse(
        String uuid,
        List<ErrorResponse> errors,
        LocalDateTime date
) {
    public static ErrorsResponse from(final List<ErrorResponse> errors) {
        return new ErrorsResponse(UUID.randomUUID().toString(), errors, LocalDateTime.now());
    }
}

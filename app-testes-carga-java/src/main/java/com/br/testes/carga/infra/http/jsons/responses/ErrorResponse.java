package com.br.testes.carga.infra.http.jsons.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String uuid,
        String field,
        String message,
        LocalDateTime date
) {
    public static ErrorResponse of(final FieldError field, final String message) {
        return new ErrorResponse(null, field.getField(), message, null);
    }

    public static ErrorResponse of(final String message) {
        return new ErrorResponse(UUID.randomUUID().toString(), null, message, LocalDateTime.now());
    }
}

package com.br.testes.carga.infra.http.jsons.responses;

import com.br.testes.carga.domains.DatabaseInfo;

public record DatabaseInfoResponse(
        String name,
        int quantity
) {
    public static DatabaseInfoResponse from(final DatabaseInfo databaseInfo) {
        return new DatabaseInfoResponse(databaseInfo.getName(), databaseInfo.getQuantity());
    }
}

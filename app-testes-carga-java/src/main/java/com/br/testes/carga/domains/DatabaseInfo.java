package com.br.testes.carga.domains;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseInfo {

    private String name;
    private int quantity;

    public static DatabaseInfo of(final String name, final int quantity) {
        return new DatabaseInfo(name, quantity);
    }


}

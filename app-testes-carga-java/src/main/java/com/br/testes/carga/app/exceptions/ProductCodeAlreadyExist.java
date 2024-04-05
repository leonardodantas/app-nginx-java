package com.br.testes.carga.app.exceptions;

public class ProductCodeAlreadyExist extends RuntimeException {

    public ProductCodeAlreadyExist(final String message) {
        super(message);
    }
}

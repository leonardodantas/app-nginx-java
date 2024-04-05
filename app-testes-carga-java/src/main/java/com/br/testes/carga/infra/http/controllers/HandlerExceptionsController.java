package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.exceptions.ProductCodeAlreadyExist;
import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.infra.http.jsons.responses.ErrorResponse;
import com.br.testes.carga.infra.http.jsons.responses.ErrorsResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionsController {

    private final MessageSource messageSource;

    public HandlerExceptionsController(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(final Exception exception) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(exception.getMessage()));
    }

    @ExceptionHandler(ProductCodeAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handlerProductCodeAlreadyExist(final ProductCodeAlreadyExist exception) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(exception.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(final ProductNotFoundException exception) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final var fields = exception.getBindingResult().getFieldErrors();

        final var errors = fields.stream()
                .map(field ->
                        ErrorResponse.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale()))
                )
                .toList();

        final var response = ErrorsResponse.from(errors);
        return ResponseEntity.badRequest().body(response);
    }
}

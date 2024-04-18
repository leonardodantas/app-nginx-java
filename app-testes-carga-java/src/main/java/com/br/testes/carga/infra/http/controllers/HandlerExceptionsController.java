package com.br.testes.carga.infra.http.controllers;

import com.br.testes.carga.app.exceptions.ProductCodeAlreadyExist;
import com.br.testes.carga.app.exceptions.ProductNotFoundException;
import com.br.testes.carga.infra.http.jsons.responses.ErrorResponse;
import com.br.testes.carga.infra.http.jsons.responses.ErrorsResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
    public ResponseEntity<ProblemDetail> handlerException(final Exception exception) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        problemDetail.setTitle("HandlerException");
        problemDetail.setProperty("error", ErrorResponse.of(exception.getMessage()));
        problemDetail.setProperty("StackTrace", exception.getStackTrace());
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(ProductCodeAlreadyExist.class)
    public ResponseEntity<ProblemDetail> handlerProductCodeAlreadyExist(final ProductCodeAlreadyExist exception) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, exception.getLocalizedMessage());
        problemDetail.setTitle("HandlerProductCodeAlreadyExist");
        problemDetail.setProperty("error", ErrorResponse.of(exception.getMessage()));
        problemDetail.setProperty("StackTrace", exception.getStackTrace());
        return ResponseEntity.unprocessableEntity().body(problemDetail);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(final ProductNotFoundException exception) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        problemDetail.setTitle("HandlerProductNotFoundException");
        problemDetail.setProperty("error", ErrorResponse.of(exception.getMessage()));
        problemDetail.setProperty("StackTrace", exception.getStackTrace());
        return ResponseEntity.badRequest().body(ErrorResponse.of(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final var fields = exception.getBindingResult().getFieldErrors();

        final var errors = fields.stream()
                .map(field ->
                        ErrorResponse.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale()))
                )
                .toList();


        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        problemDetail.setTitle("HandleMethodArgumentNotValidException");
        problemDetail.setProperty("error", ErrorsResponse.from(errors));
        problemDetail.setProperty("StackTrace", exception.getStackTrace());

        return ResponseEntity.badRequest().body(problemDetail);
    }
}

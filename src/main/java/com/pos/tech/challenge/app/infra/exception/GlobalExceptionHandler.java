package com.pos.tech.challenge.app.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."
        );

        problemDetail.setTitle("Erro de Validação");
        problemDetail.setType(URI.create("https://api.techchallenge.com/errors/bad-request"));
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        var invalidFields = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        problemDetail.setProperty("invalid-params", invalidFields);

        return problemDetail;
    }

    @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
    public ProblemDetail handleBusinessExceptions(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("não encontrado")) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("credenciais")) {
            status = HttpStatus.UNAUTHORIZED;
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problemDetail.setTitle("Erro de Regra de Negócio");
        problemDetail.setType(URI.create("https://api.techchallenge.com/errors/business-rule"));
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
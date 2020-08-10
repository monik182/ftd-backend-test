package com.ftd.test.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        String constraintName = exception.getConstraintName();
        String value = constraintName.equals("customer_document_id_key")
                ? "número de documento"
                : constraintName.equals("customer_user_name_key")
                ? "nombre de usuario"
                : constraintName.equals("customer_email_key")
                ? "email"
                : constraintName;


        System.out.println("*********** getConstraintName: " + exception.getConstraintName());
        String error = "Ya existe un cliente con el mismo " + value;
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String field = exception.getBindingResult().getFieldError().getField();
        String defaultError = exception.getBindingResult().getFieldError().getDefaultMessage();
        String error = field + ": " + (!defaultError.isEmpty() ? defaultError : "campo obligatorio");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
//        System.out.println("getMessage >>>>>>>" + exception.getMessage());
        return new ResponseEntity<>("Elemento no encontrado.", HttpStatus.NOT_FOUND);
    }

}

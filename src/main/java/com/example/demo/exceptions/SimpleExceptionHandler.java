package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SimpleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        HttpStatus status;
        if (ex instanceof AppException) status = ((AppException) ex).getStatus();
        else status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), status, request);
    }
}

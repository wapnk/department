package com.example.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}

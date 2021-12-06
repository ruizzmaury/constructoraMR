package com.example.constructora.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    public ApiException(String message, Throwable throwable,
                        HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
    }
}

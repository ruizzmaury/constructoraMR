package com.example.constructora.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public record ApiException(String message, Throwable throwable,
                           HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
    // EXCEPTION QUE VERÁ EL CLIENT
//    @Override
//    public String toString() {
//        return "ApiException{" +
//                "message='" + message + '\'' +
//                ", throwable=" + throwable +
//                ", httpStatus=" + httpStatus +
//                ", zonedDateTime=" + zonedDateTime +
//                '}';
//    }
}

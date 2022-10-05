package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.exception.GenericException;

public class ResponseUtils {

    public static <T> ResponseEntity<?> getResponseEntity(T body) {
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static ResponseEntity<?> getResponseEntity(GenericException genericException) {
        return ResponseEntity.status(HttpStatus.valueOf(genericException.getCode()))
                .body(genericException.getLocalizedMessage());
    }

}

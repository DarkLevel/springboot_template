package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.exception.GenericException;

public class ResponseUtils {

    private ResponseUtils() {

    }

    public static <T> ResponseEntity<ResponseObject> getResponseEntity(T body) {
        return getResponseEntityObject(200, body);
    }

    public static ResponseEntity<ResponseObject> getResponseEntity(GenericException genericException) {
        return getResponseEntityObject(genericException.getCode(), genericException.getLocalizedMessage());
    }

    private static ResponseEntity<ResponseObject> getResponseEntityObject(int status, Object object) {
        return ResponseEntity.status(HttpStatus.valueOf(status)).body(new ResponseObject(status, object));
    }

}

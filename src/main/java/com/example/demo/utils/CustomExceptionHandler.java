package com.example.demo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.GenericException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseObject> handleSecurityException(Exception e) {
    GenericException genericException;

    if (e instanceof BadCredentialsException) {
      genericException = new GenericException(e.getMessage(), e, 401);
    } else if (e instanceof AccessDeniedException) {
      genericException = new GenericException(e.getMessage(), e, 403);
    } else if (e instanceof SignatureException) {
      genericException = new GenericException(e.getMessage(), e, 403);
    } else if (e instanceof JwtException) {
      genericException = new GenericException(e.getMessage(), e, 403);
    } else {
      genericException = new GenericException(e.getMessage(), e, 500);
    }

    return ResponseUtils.getResponseEntity(genericException);
  }

}

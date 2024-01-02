package com.example.demo.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.IAuthController;
import com.example.demo.exception.GenericException;
import com.example.demo.model.AuthModel;
import com.example.demo.service.impl.AuthService;
import com.example.demo.utils.ResponseObject;
import com.example.demo.utils.ResponseUtils;

@CrossOrigin
@RestController
public class AuthController implements IAuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public ResponseEntity<ResponseObject> login(AuthModel authModel) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(authService.getAccessToken(authModel));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> refreshToken(String refreshToken) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(authService.getAccessToken(refreshToken));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<ResponseObject> revokeRefreshToken(String refreshToken) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(authService.revokeRefreshToken(refreshToken));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

}

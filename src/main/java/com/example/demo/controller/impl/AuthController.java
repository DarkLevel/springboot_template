package com.example.demo.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  protected AuthService service;

  @Override
  public ResponseEntity<ResponseObject> login(AuthModel authModel) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(service.getToken(authModel));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

}

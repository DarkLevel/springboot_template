package com.example.demo.service;

import com.example.demo.exception.GenericException;
import com.example.demo.model.AuthModel;

public interface IAuthService {

  public AuthModel getToken(AuthModel authModel) throws GenericException;

}

package com.example.demo.service;

import com.example.demo.exception.GenericException;
import com.example.demo.model.AuthModel;

public interface IAuthService {

  public AuthModel getAccessToken(AuthModel authModel) throws GenericException;

  public AuthModel getAccessToken(String refreshToken) throws GenericException;

  public int revokeRefreshToken(String refreshToken) throws GenericException;

}

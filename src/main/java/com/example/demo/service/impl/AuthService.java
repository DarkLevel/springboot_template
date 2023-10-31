package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.exception.GenericException;
import com.example.demo.model.AuthModel;
import com.example.demo.service.IAuthService;

@Service
public class AuthService implements IAuthService {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public String getToken(AuthModel authModel) throws GenericException {
    try {
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));

      if (authentication.isAuthenticated()) {
        return tokenService.generateToken(authModel.getUsername());
      } else {
        throw new GenericException("User not found", 404);
      }
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

}

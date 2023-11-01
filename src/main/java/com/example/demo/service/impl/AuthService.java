package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserRoleDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.AuthModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IAuthService;
import com.example.demo.service.IUserService;

@Service
public class AuthService implements IAuthService {

  @Value("${secret}")
  private String secret;

  @Value("${token_expiration}")
  private Integer tokenExpiration;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IUserRoleDao userRoleDao;

  @Override
  public AuthModel getToken(AuthModel authModel) throws GenericException {
    try {
      UserModel userModel = userService.getByUsername(authModel.getUsername());

      if (userModel == null) {
        throw new GenericException("User not found", 404);
      }

      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));

      if (!authentication.isAuthenticated()) {
        throw new GenericException("User not authenticated", 401);
      }

      List<UserRoleModel> lUserRoleModel = userRoleDao.findByUserModel(userModel);

      Collection<String> roles = lUserRoleModel.stream().map(role -> role.getRoleModel().getName())
          .collect(Collectors.toList());
      Date issuedAt = new Date();
      Date expiration = new Date(System.currentTimeMillis() + tokenExpiration * 1000);
      String token = tokenService.generateToken(authModel.getUsername(), issuedAt, expiration, secret);

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX");

      authModel.setUsername(tokenService.extractUsername(token, secret));
      authModel.setRoles(roles);
      authModel.setIssuedAt(sdf.format(issuedAt));
      authModel.setExpiration(sdf.format(expiration));
      authModel.setAccess_token(token);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }

    return authModel;
  }

}

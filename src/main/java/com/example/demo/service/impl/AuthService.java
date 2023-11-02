package com.example.demo.service.impl;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IRefreshTokenDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.AccessTokenModel;
import com.example.demo.model.AuthModel;
import com.example.demo.model.RefreshTokenModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IAuthService;
import com.example.demo.service.IUserService;
import com.example.demo.utils.Utilities;

@Service
public class AuthService implements IAuthService {

  @Value("${secret}")
  private String secret;

  @Value("${access_token_expiration}")
  private Long accessTokenExpiration;

  @Value("${refresh_token_expiration}")
  private Long refreshTokenExpiration;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IUserRoleDao userRoleDao;

  @Autowired
  private IRefreshTokenDao refreshTokenDao;

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
      Date expiration = new Date(System.currentTimeMillis() + accessTokenExpiration * 1000);
      String token = tokenService.generateToken(authModel.getUsername(), issuedAt, expiration, secret);
      String username = tokenService.extractUsername(token, secret);

      authModel.setUsername(username);
      authModel.setRoles(roles);
      authModel.setAccess_token(new AccessTokenModel(token,
          Utilities.formatDateToISOWithoutMillis(issuedAt),
          Utilities.formatDateToISOWithoutMillis(expiration)));
      authModel.setRefresh_token(createRefreshToken(username));
    } catch (GenericException e) {
      throw e;
    } catch (BadCredentialsException e) {
      throw new GenericException(e.getMessage(), e, 401);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }

    return authModel;
  }

  public RefreshTokenModel createRefreshToken(String username) {
    RefreshTokenModel refreshTokenModel;

    try {
      refreshTokenModel = new RefreshTokenModel(UUID.randomUUID().toString(),
          Instant.now().plusMillis(refreshTokenExpiration * 1000),
          userService.getByUsername(username));
      refreshTokenModel.setEnabled(true);
      refreshTokenModel = refreshTokenDao.save(refreshTokenModel);
    } catch (GenericException e) {
      refreshTokenModel = null;
    }

    return refreshTokenModel;
  }

}

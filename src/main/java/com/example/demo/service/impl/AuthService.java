package com.example.demo.service.impl;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IRefreshTokenDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.AccessTokenModel;
import com.example.demo.model.AuthModel;
import com.example.demo.model.RefreshTokenModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IAuthService;
import com.example.demo.utils.Utilities;

import jakarta.transaction.Transactional;

@Service
public class AuthService implements IAuthService {

  private final String tokenSecret;
  private final Long accessTokenExpiration;
  private final Long refreshTokenExpiration;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final IUserDao userDao;
  private final IUserRoleDao userRoleDao;
  private final IRefreshTokenDao refreshTokenDao;

  public AuthService(@Value("${tokenSecret}") String tokenSecret,
      @Value("${access_token_expiration}") Long accessTokenExpiration,
      @Value("${refresh_token_expiration}") Long refreshTokenExpiration, AuthenticationManager authenticationManager,
      TokenService tokenService, IUserDao userDao, IUserRoleDao userRoleDao, IRefreshTokenDao refreshTokenDao) {
    this.tokenSecret = tokenSecret;
    this.accessTokenExpiration = accessTokenExpiration;
    this.refreshTokenExpiration = refreshTokenExpiration;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.userDao = userDao;
    this.userRoleDao = userRoleDao;
    this.refreshTokenDao = refreshTokenDao;
  }

  @Override
  public AuthModel getAccessToken(AuthModel authModel) throws GenericException {
    try {
      UserModel userModel = userDao.findByUsername(authModel.getUsername());

      if (userModel == null) {
        throw new GenericException("User not found", 404);
      }

      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));

      if (!authentication.isAuthenticated()) {
        throw new GenericException("User not authenticated", 401);
      }

      authModel = getAuthModel(authModel, userModel, null);
    } catch (GenericException e) {
      throw e;
    } catch (BadCredentialsException e) {
      throw new GenericException(e.getMessage(), e, 401);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }

    return authModel;
  }

  public AuthModel getAccessToken(String refreshToken) throws GenericException {
    try {
      RefreshTokenModel refreshTokenModel = refreshTokenDao.findByToken(refreshToken);

      if (refreshTokenModel == null) {
        throw new GenericException("Token not found", 404);
      }

      if (refreshTokenModel.getExpiration().compareTo(Instant.now()) < 0) {
        refreshTokenDao.delete(refreshTokenModel.getId());
        throw new GenericException("The refresh token is not valid", 401);
      }

      AuthModel authModel = new AuthModel(refreshTokenModel.getUserModel().getUsername(), null, null, null,
          refreshTokenModel);

      return getAuthModel(authModel, refreshTokenModel.getUserModel(), refreshTokenModel);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public int revokeRefreshToken(String refreshToken) throws GenericException {
    try {
      RefreshTokenModel refreshTokenModel = refreshTokenDao.findByToken(refreshToken);

      if (refreshTokenModel == null) {
        throw new GenericException("Token not found", 404);
      }

      refreshTokenDao.delete(refreshTokenModel.getId());
      return 1;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  private AuthModel getAuthModel(AuthModel authModel, UserModel userModel, RefreshTokenModel refreshTokenModel)
      throws GenericException {
    try {
      List<UserRoleModel> lUserRoleModel = userRoleDao.findByUserModel(userModel);

      Collection<String> roles = lUserRoleModel.stream().map(role -> role.getRoleModel().getName())
          .collect(Collectors.toList());
      Date issuedAt = new Date();
      Date expiration = new Date(System.currentTimeMillis() + accessTokenExpiration * 1000);
      String accessToken = tokenService.generateToken(authModel.getUsername(), issuedAt, expiration, tokenSecret);
      String username = tokenService.extractUsername(accessToken, tokenSecret);

      authModel.setUsername(username);
      authModel.setRoles(roles);
      authModel.setAccessToken(new AccessTokenModel(accessToken,
          Utilities.formatDateToISOWithoutMillis(issuedAt),
          Utilities.formatDateToISOWithoutMillis(expiration)));
      authModel.setRefreshToken(refreshTokenModel != null ? refreshTokenModel : createRefreshToken(username));

      return authModel;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  private RefreshTokenModel createRefreshToken(String username) {
    RefreshTokenModel refreshTokenModel = new RefreshTokenModel(UUID.randomUUID().toString(),
        Instant.now().plusMillis(refreshTokenExpiration * 1000),
        userDao.findByUsername(username));

    RefreshTokenModel refreshTokenModelAux = refreshTokenDao.findByUserModel(userDao.findByUsername(username));

    if (refreshTokenModelAux == null) {
      refreshTokenModel = refreshTokenDao.save(refreshTokenModel);
    } else {
      refreshTokenModelAux.setToken(refreshTokenModel.getToken());
      refreshTokenModelAux.setExpiration(refreshTokenModel.getExpiration());
      refreshTokenModel = refreshTokenDao.save(refreshTokenModelAux);
    }

    return refreshTokenModel;
  }

}

package com.example.demo.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IRefreshTokenDao;
import com.example.demo.model.RefreshTokenModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IRefreshTokenRepository;

@Component
public class RefreshTokenDao extends GenericDao<RefreshTokenModel, Long> implements IRefreshTokenDao {

  private final IRefreshTokenRepository refreshTokenRepository;

  public RefreshTokenDao(IRefreshTokenRepository refreshTokenRepository) {
    super(refreshTokenRepository);
    this.refreshTokenRepository = refreshTokenRepository;
  }

  @Override
  public RefreshTokenModel findByToken(String token) {
    Optional<RefreshTokenModel> refreshTokenModel = refreshTokenRepository.findByToken(token);
    return refreshTokenModel.isPresent() ? refreshTokenModel.get() : null;
  }

  @Override
  public RefreshTokenModel findByUserModel(UserModel userModel) {
    Optional<RefreshTokenModel> refreshTokenModel = refreshTokenRepository.findByUserModel(userModel);
    return refreshTokenModel.isPresent() ? refreshTokenModel.get() : null;
  }

}

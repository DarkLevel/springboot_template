package com.example.demo.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.IRefreshTokenDao;
import com.example.demo.model.RefreshTokenModel;
import com.example.demo.repository.IRefreshTokenRepository;

@Component
public class RefreshTokenDao extends GenericDao<RefreshTokenModel, Long> implements IRefreshTokenDao {

  @Autowired
  private IRefreshTokenRepository refreshTokenRepository;

  @Override
  public RefreshTokenModel findByRefreshToken(String token) {
    Optional<RefreshTokenModel> refreshTokenModel = refreshTokenRepository.findByRefreshToken(token);
    return refreshTokenModel.isPresent() ? refreshTokenModel.get() : null;
  }

}

package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.RefreshTokenModel;

public interface IRefreshTokenRepository extends IGenericRepository<RefreshTokenModel, Long> {

  public Optional<RefreshTokenModel> findByToken(String token);

}

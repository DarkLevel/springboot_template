package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.RefreshTokenModel;
import com.example.demo.model.UserModel;

public interface IRefreshTokenRepository extends IGenericRepository<RefreshTokenModel, Long> {

  public Optional<RefreshTokenModel> findByToken(String token);

  public Optional<RefreshTokenModel> findByUserModel(UserModel userModel);

}

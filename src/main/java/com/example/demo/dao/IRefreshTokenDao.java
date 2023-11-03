package com.example.demo.dao;

import com.example.demo.model.RefreshTokenModel;
import com.example.demo.model.UserModel;

public interface IRefreshTokenDao extends IGenericDao<RefreshTokenModel, Long> {

  public RefreshTokenModel findByToken(String token);

  public RefreshTokenModel findByUserModel(UserModel userModel);

}

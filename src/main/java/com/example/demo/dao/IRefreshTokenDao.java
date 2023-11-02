package com.example.demo.dao;

import com.example.demo.model.RefreshTokenModel;

public interface IRefreshTokenDao extends IGenericDao<RefreshTokenModel, Long> {

  public RefreshTokenModel findByToken(String token);

}

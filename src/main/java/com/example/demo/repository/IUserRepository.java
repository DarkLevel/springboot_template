package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.UserModel;

public interface IUserRepository extends IGenericRepository<UserModel, Long> {

  public List<UserModel> findByUsername(String username);

}

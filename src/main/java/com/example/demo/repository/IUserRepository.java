package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.UserModel;

public interface IUserRepository extends IGenericRepository<UserModel, Long> {

  public Optional<UserModel> findByUsername(String username);

}

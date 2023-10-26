package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;

public interface IUserRoleRepository extends IGenericRepository<UserRoleModel, Long> {

  public List<UserRoleModel> findByUserModel(UserModel userModel);

}

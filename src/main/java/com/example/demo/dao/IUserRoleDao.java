package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;

public interface IUserRoleDao extends IGenericDao<UserRoleModel, Long> {

  public List<UserRoleModel> findByUserModel(UserModel userModel);

}

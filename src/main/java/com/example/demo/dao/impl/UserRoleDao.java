package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IUserRoleDao;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.repository.IUserRoleRepository;

@Component
public class UserRoleDao extends GenericDao<UserRoleModel, Long> implements IUserRoleDao {

  private final IUserRoleRepository userRoleRepository;

  public UserRoleDao(IUserRoleRepository userRoleRepository) {
    super(userRoleRepository);
    this.userRoleRepository = userRoleRepository;
  }

  @Override
  public List<UserRoleModel> findByUserModel(UserModel userModel) {
    return userRoleRepository.findByUserModel(userModel);
  }

}
